package com.minhky.core.network.di

import com.minhky.core.network.service.AuthService
import com.minhky.core.network.service.RefreshTokenService
import com.minhky.core.network.service.UserService
import com.minhky.core.network.scope.RetrofitAuth
import com.minhky.core.network.scope.RetrofitNoAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Dagger module for providing network-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    /**
     * Provides a singleton instance of RefreshTokenService.
     *
     * @param retrofit The Retrofit instance without authentication.
     * @return The RefreshTokenService instance.
     */
    @Provides
    @Singleton
    fun provideRefreshTokenDataSource(@RetrofitNoAuth retrofit: Retrofit): RefreshTokenService =
        retrofit.create(RefreshTokenService::class.java)

    /**
     * Provides a singleton instance of AuthService.
     *
     * @param retrofit The Retrofit instance with authentication.
     * @return The AuthService instance.
     */
    @Provides
    @Singleton
    fun provideAuthDataSource(@RetrofitAuth retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    /**
     * Provides a singleton instance of UserService.
     *
     * @param retrofit The Retrofit instance with authentication.
     * @return The UserService instance.
     */
    @Provides
    @Singleton
    fun provideUserApi(@RetrofitAuth retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

}