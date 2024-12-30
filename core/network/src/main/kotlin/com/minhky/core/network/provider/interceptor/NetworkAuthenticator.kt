package com.minhky.core.network.provider.interceptor

import com.minhky.core.network.service.RefreshTokenService
import com.minhky.core.network.model.request.RefreshTokenRequest
import com.minhky.core.preferences.AppSharedPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class NetworkAuthenticator(
    private val prefs: AppSharedPreferences,
    private val refreshTokenService: RefreshTokenService
) : Authenticator {

    private val accessToken: String?
        get() = prefs.accessToken

    private val refreshToken: String?
        get() = prefs.refreshToken

    override fun authenticate(route: Route?, response: Response): Request? {
        //Handle check token expired and call refresh token
        return synchronized(this) {
            if (!isRefreshNeeded(response)) {
                return response.request
                    .newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .build()
            }
            try {
                val res = refreshTokenService.refreshTokenAsync(
                    refreshToken = RefreshTokenRequest(refreshToken)
                ).execute()

                if (res.isSuccessful) {
                    runBlocking {
                        val authModel = res.body()
                        val authorization = res.body()?.accessToken
                        authModel?.let {
                            prefs.authenticated(
                                accessToken = it.accessToken,
                                refreshToken = it.refreshToken,
                                userId = it.userId
                            )
                        }

                        //re-call api when token expired
                        response.request
                            .newBuilder()
                            .header("Authorization", "Bearer $authorization")
                            .build()
                    }
                } else {
                    null
                }
            } catch (ex: Throwable) {
                null
            }
        }
    }

    private fun isRefreshNeeded(response: Response) : Boolean {
        val isRefreshNeeded = response.request.header("Authorization") == "Bearer $accessToken"
        return isRefreshNeeded
    }
}