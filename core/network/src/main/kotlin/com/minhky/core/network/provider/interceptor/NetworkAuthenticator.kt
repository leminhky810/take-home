package com.minhky.core.network.provider.interceptor

import com.minhky.core.network.BuildConfig
import com.minhky.core.network.service.RefreshTokenService
import com.minhky.core.network.model.request.RefreshTokenRequest
import com.minhky.core.preferences.AppSharedPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Authenticator for handling token refresh when the access token expires.
 *
 * @property prefs The shared preferences for storing authentication tokens.
 * @property refreshTokenService The service for refreshing tokens.
 */
class NetworkAuthenticator(
    private val prefs: AppSharedPreferences,
    private val refreshTokenService: RefreshTokenService
) : Authenticator {

    /**
     * Retrieves the current access token from shared preferences or build config.
     */
    private val accessToken: String?
        get() = prefs.accessToken ?: BuildConfig.GITHUB_TOKEN

    /**
     * Retrieves the current refresh token from shared preferences.
     */
    private val refreshToken: String?
        get() = prefs.refreshToken

    /**
     * Authenticates the request by checking if the token needs to be refreshed.
     *
     * @param route The route for the request.
     * @param response The response that triggered the authentication.
     * @return The new request with updated authorization header or null if authentication fails.
     */
    override fun authenticate(route: Route?, response: Response): Request? {
        // Handle check token expired and call refresh token
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

                        // Re-call API when token expired
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

    /**
     * Checks if the token refresh is needed based on the response.
     *
     * @param response The response to check.
     * @return True if refresh is needed, false otherwise.
     */
    private fun isRefreshNeeded(response: Response): Boolean {
        val isRefreshNeeded = response.request.header("Authorization") == "Bearer $accessToken"
        return isRefreshNeeded
    }
}