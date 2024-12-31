package com.minhky.core.network.provider.interceptor

import com.minhky.core.preferences.AppSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import com.minhky.core.network.BuildConfig

/**
 * Interceptor for adding headers to network requests.
 *
 * @property prefs The shared preferences for storing authentication tokens.
 */
class HeaderInterceptor (
    private val prefs: AppSharedPreferences
) : Interceptor {

    /**
     * Retrieves the current access token from shared preferences or build config.
     */
    private val accessToken: String?
        get() = prefs.accessToken ?: BuildConfig.GITHUB_TOKEN

    /**
     * Intercepts the request to add necessary headers.
     *
     * @param chain The interceptor chain.
     * @return The response after adding headers to the request.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            addHeader("Content-Type", "application/json;charset=utf-8")
            if (accessToken?.isNotBlank() == true) {
                addHeader("Authorization", "Bearer $accessToken")
            }
        }
        return chain.proceed(request.build())
    }
}