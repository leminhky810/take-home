package com.minhky.core.data.di

import com.minhky.core.data.repo.UserRepository
import com.minhky.core.data.repo.UserRepositoryImp
import com.minhky.core.data.util.ConnectivityManagerNetworkMonitor
import com.minhky.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    abstract fun bindsUserRepository(
        impl: UserRepositoryImp
    ): UserRepository


}