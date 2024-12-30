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

@Module
@InstallIn(SingletonComponent::class)
internal object OkHttpClientModule {

    @NetworkAuthenticatorScope
    @Provides
    @Singleton
    fun providerAuthenticator(
        prefs: AppSharedPreferences,
        dataSource: RefreshTokenService
    ): Authenticator = NetworkAuthenticator(prefs, dataSource)

    @HeaderInterceptorAuth
    @Provides
    @Singleton
    fun providerHeaderInterceptorWithAut(
        prefs: AppSharedPreferences
    ): Interceptor = HeaderInterceptor(prefs)

    @HeaderInterceptorNoAuth
    @Provides
    @Singleton
    fun providerHeaderInterceptorWithoutAuth(
    ): Interceptor =
        HeaderNoAuthInterceptor()

    @HttpLoggingInterceptorAuth
    @Provides
    @Singleton
    fun providerHttpLoggingInterceptorWithAut(
    ): Interceptor = createHttpLoggingInterceptor()

    @HttpLoggingInterceptorNoAuth
    @Provides
    @Singleton
    fun providerHttpLoggingInterceptorWithoutAut(
    ): Interceptor = createHttpLoggingInterceptor()

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