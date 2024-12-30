package com.minhky.core.network.provider.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderNoAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder().apply {
            addHeader("Content-Type", "application/json;charset=utf-8")
//            addHeader("X-Requested-With", "XMLHttpRequest")
        }
        return chain.proceed(request.build())

//        var response = chain.proceed(request)

//        var tryCount = 0
//        while (!response.isSuccessful && tryCount < 3 && response.code in NetworkStatus.UnAuthorized.code) {
//
//            tryCount++;
//            // retry the request
//            response.close()
//            response = chain.proceed(request);
//        }
//        return  response
    }

}