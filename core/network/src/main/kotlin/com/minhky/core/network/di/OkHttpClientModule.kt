package com.minhky.core.network.di

import com.minhky.core.network.service.RefreshTokenService
import com.minhky.core.network.provider.createHttpLoggingInterceptor
import com.minhky.core.network.provider.createOkHttpClientWithAuthBuilder
import com.minhky.core.network.provider.createOkHttpClientWithoutAuthBuilder
import com.minhky.core.network.provider.interceptor.HeaderInterceptor
import com.minhky.core.network.provider.interceptor.HeaderNoAuthInterceptor
import com.minhky.core.network.provider.interceptor.NetworkAuthenticator
import com.minhky.core.network.scope.HeaderInterceptorAuth
import com.minhky.core.network.scope.HeaderInterceptorNoAuth
import com.minhky.core.network.scope.HttpLoggingInterceptorAuth
import com.minhky.core.network.scope.HttpLoggingInterceptorNoAuth
import com.minhky.core.network.scope.NetworkAuthenticatorScope
import com.minhky.core.network.scope.OkHttpClientAuth
import com.minhky.core.network.scope.OkHttpClientNoAuth
import com.minhky.core.preferences.AppSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Call
import okhttp3.Interceptor
import javax.inject.Singleton

/**
 * Dagger module for providing OkHttpClient dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object OkHttpClientModule {

    /**
     * Provides a singleton instance of NetworkAuthenticator.
     *
     * @param prefs The shared preferences for the application.
     * @param dataSource The service to refresh tokens.
     * @return The NetworkAuthenticator instance.
     */
    @NetworkAuthenticatorScope
    @Provides
    @Singleton
    fun providerAuthenticator(
        prefs: AppSharedPreferences,
        dataSource: RefreshTokenService
    ): Authenticator = NetworkAuthenticator(prefs, dataSource)

    /**
     * Provides a singleton instance of HeaderInterceptor with authentication.
     *
     * @param prefs The shared preferences for the application.
     * @return The HeaderInterceptor instance.
     */
    @HeaderInterceptorAuth
    @Provides
    @Singleton
    fun providerHeaderInterceptorWithAut(
        prefs: AppSharedPreferences
    ): Interceptor = HeaderInterceptor(prefs)

    /**
     * Provides a singleton instance of HeaderNoAuthInterceptor without authentication.
     *
     * @return The HeaderNoAuthInterceptor instance.
     */
    @HeaderInterceptorNoAuth
    @Provides
    @Singleton
    fun providerHeaderInterceptorWithoutAuth(
    ): Interceptor =
        HeaderNoAuthInterceptor()

    /**
     * Provides a singleton instance of HttpLoggingInterceptor with authentication.
     *
     * @return The HttpLoggingInterceptor instance.
     */
    @HttpLoggingInterceptorAuth
    @Provides
    @Singleton
    fun providerHttpLoggingInterceptorWithAut(
    ): Interceptor = createHttpLoggingInterceptor()

    /**
     * Provides a singleton instance of HttpLoggingInterceptor without authentication.
     *
     * @return The HttpLoggingInterceptor instance.
     */
    @HttpLoggingInterceptorNoAuth
    @Provides
    @Singleton
    fun providerHttpLoggingInterceptorWithoutAut(
    ): Interceptor = createHttpLoggingInterceptor()

    /**
     * Provides a singleton instance of OkHttpClient with authentication.
     *
     * @param authenticator The authenticator for network requests.
     * @param header The header interceptor with authentication.
     * @param logging The logging interceptor with authentication.
     * @return The Call.Factory instance.
     */
    @OkHttpClientAuth
    @Provides
    @Singleton
    fun provideOkHttpClientWithAuthBuilder(
        @NetworkAuthenticatorScope authenticator: Authenticator,
        @HeaderInterceptorAuth header: Interceptor,
        @HttpLoggingInterceptorAuth logging: Interceptor
    ): Call.Factory = createOkHttpClientWithAuthBuilder(
        authenticator = authenticator,
        header = header,
        logging = logging
    )

    /**
     * Provides a singleton instance of OkHttpClient without authentication.
     *
     * @param header The header interceptor without authentication.
     * @param logging The logging interceptor without authentication.
     * @return The Call.Factory instance.
     */
    @OkHttpClientNoAuth
    @Provides
    @Singleton
    fun provideOkHttpClientNoAuthBuilder(
        @HeaderInterceptorNoAuth header: Interceptor,
        @HttpLoggingInterceptorNoAuth logging: Interceptor
    ): Call.Factory = createOkHttpClientWithoutAuthBuilder(
        header = header,
        logging = logging
    )

}