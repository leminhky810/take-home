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

/**
 * Dagger module for providing Retrofit instances.
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "Endpoint"

    /**
     * Provides the backend URL.
     *
     * @return The backend URL from BuildConfig.
     */
    @Named(BASE_URL)
    @Provides
    @Singleton
    fun provideEndpoint() = BuildConfig.BACKEND_URL

    /**
     * Provides a singleton instance of Json with specific configuration.
     *
     * @return The Json instance.
     */
    @Provides
    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
    }

    /**
     * Provides a singleton instance of Retrofit with authentication.
     *
     * @param baseUrl The base URL for the Retrofit instance.
     * @param okhttpCallFactory The lazy-initialized Call.Factory instance with authentication.
     * @param json The Json instance for serialization.
     * @return The Retrofit instance with authentication.
     */
    @RetrofitAuth
    @Provides
    @Singleton
    fun provideRetrofitAuth(
        @Named(BASE_URL) baseUrl: String,
        @OkHttpClientAuth okhttpCallFactory: dagger.Lazy<Call.Factory>,
        json: Json
    ): Retrofit = createRetrofit(baseUrl, json, okhttpCallFactory)

    /**
     * Provides a singleton instance of Retrofit without authentication.
     *
     * @param baseUrl The base URL for the Retrofit instance.
     * @param okhttpCallFactory The lazy-initialized Call.Factory instance without authentication.
     * @param json The Json instance for serialization.
     * @return The Retrofit instance without authentication.
     */
    @RetrofitNoAuth
    @Provides
    @Singleton
    fun provideRetrofitNoAuth(
        @Named(BASE_URL) baseUrl: String,
        @OkHttpClientNoAuth okhttpCallFactory: dagger.Lazy<Call.Factory>,
        json: Json
    ) = createRetrofit(baseUrl, json, okhttpCallFactory)

}