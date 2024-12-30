package com.minhky.core.network.provider.interceptor

import com.minhky.core.preferences.AppSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor (
    private val prefs: AppSharedPreferences
) : Interceptor {

    private val accessToken: String?
        get() = prefs.accessToken

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            addHeader("Content-Type", "application/json;charset=utf-8")
//            addHeader("X-Requested-With", "XMLHttpRequest")
            if (accessToken?.isNotBlank() == true) {
                addHeader("Authorization", "Bearer $accessToken")
            }
        }
        return chain.proceed(request.build())
    }
}