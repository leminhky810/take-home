package com.minhky.core.network.scope

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EndpointInfo

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RetrofitAuth

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RetrofitNoAuth