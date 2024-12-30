package com.minhky.core.network.provider

import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

fun createRetrofit(
    baseUrl: String,
    json: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>) : Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType()),
        )
        .build()