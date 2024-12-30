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

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRefreshTokenDataSource(@RetrofitNoAuth retrofit: Retrofit): RefreshTokenService =
        retrofit.create(RefreshTokenService::class.java)

    @Provides
    @Singleton
    fun provideAuthDataSource(@RetrofitAuth retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideUserApi(@RetrofitAuth retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

}