package com.minhky.core.network.provider

import com.minhky.core.network.BuildConfig
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIMEOUT_SEC       = 45L // in seconds
private const val DEFAULT_READ_TIMEOUT_SEC  = 45L // in seconds
private const val DEFAULT_WRITE_TIMEOUT_SEC = 45L // in seconds
private val timeUnit = TimeUnit.SECONDS

fun createOkHttpClientWithAuthBuilder(authenticator: Authenticator, header: Interceptor, logging: Interceptor) =
    OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIMEOUT_SEC, timeUnit)
        .readTimeout(DEFAULT_READ_TIMEOUT_SEC, timeUnit)
        .writeTimeout(DEFAULT_WRITE_TIMEOUT_SEC, timeUnit)
        .authenticator(authenticator)
        .addInterceptor(logging)
        .addInterceptor(header)
        .build()

fun createOkHttpClientWithoutAuthBuilder(header: Interceptor, logging: Interceptor) =
    OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIMEOUT_SEC, timeUnit)
        .readTimeout(DEFAULT_READ_TIMEOUT_SEC, timeUnit)
        .writeTimeout(DEFAULT_WRITE_TIMEOUT_SEC, timeUnit)
        .addInterceptor(header)
        .addInterceptor(logging)
        .build()

fun createHttpLoggingInterceptor() =
    HttpLoggingInterceptor().apply {
        level = when (BuildConfig.DEBUG) {
            true -> {
                HttpLoggingInterceptor.Level.BODY
            }
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }