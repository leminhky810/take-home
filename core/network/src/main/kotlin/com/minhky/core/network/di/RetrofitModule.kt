package com.minhky.core.network.di

import com.minhky.core.network.BuildConfig
import com.minhky.core.network.provider.createRetrofit
import com.minhky.core.network.scope.OkHttpClientAuth
import com.minhky.core.network.scope.OkHttpClientNoAuth
import com.minhky.core.network.scope.RetrofitAuth
import com.minhky.core.network.scope.RetrofitNoAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "Endpoint"

    @Named(BASE_URL)
    @Provides
    @Singleton
    fun provideEndpoint() = BuildConfig.BACKEND_URL

    @Provides
    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
    }

    @RetrofitAuth
    @Provides
    @Singleton
    fun provideRetrofitAuth(
        @Named(BASE_URL) baseUrl: String,
        @OkHttpClientAuth okhttpCallFactory: dagger.Lazy<Call.Factory>,
        json: Json
    ): Retrofit = createRetrofit(baseUrl, json, okhttpCallFactory)

    @RetrofitNoAuth
    @Provides
    @Singleton
    fun provideRetrofitNoAuth(
        @Named(BASE_URL) baseUrl: String,
        @OkHttpClientNoAuth okhttpCallFactory: dagger.Lazy<Call.Factory>,
        json: Json
    ) = createRetrofit(baseUrl, json, okhttpCallFactory)

}