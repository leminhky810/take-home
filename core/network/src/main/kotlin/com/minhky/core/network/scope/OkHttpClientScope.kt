package com.minhky.core.network.scope

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkAuthenticatorScope

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HeaderInterceptorAuth

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HeaderInterceptorNoAuth

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HttpLoggingInterceptorAuth

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HttpLoggingInterceptorNoAuth

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpClientAuth

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpClientNoAuth