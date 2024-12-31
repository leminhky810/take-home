package com.minhky.core.data.di

import com.minhky.core.data.repo.UserRepository
import com.minhky.core.data.repo.UserRepositoryImp
import com.minhky.core.data.util.ConnectivityManagerNetworkMonitor
import com.minhky.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger module for providing data-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    /**
     * Provides an implementation of the NetworkMonitor interface.
     *
     * @param networkMonitor The implementation of ConnectivityManagerNetworkMonitor.
     * @return The NetworkMonitor implementation.
     */
    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    /**
     * Provides an implementation of the UserRepository interface.
     *
     * @param impl The implementation of UserRepositoryImp.
     * @return The UserRepository implementation.
     */
    @Binds
    abstract fun bindsUserRepository(
        impl: UserRepositoryImp
    ): UserRepository
}