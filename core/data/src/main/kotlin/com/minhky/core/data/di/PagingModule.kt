package com.minhky.core.data.di

import com.minhky.core.data.paging.UserPagingSource
import com.minhky.core.data.paging.UserRemoteMediator
import com.minhky.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module for providing paging-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    /**
     * Provides a singleton instance of UserPagingSource.
     *
     * @param userDao The DAO for accessing user data.
     * @param userRemoteMediator The remote mediator for handling user data synchronization.
     * @return A UserPagingSource instance.
     */
    @Provides
    @Singleton
    fun provideUserPagingSource(
        userDao: UserDao,
        userRemoteMediator: UserRemoteMediator
    ): UserPagingSource {
        return UserPagingSource(
            userDao = userDao,
            userRemoteMediator = userRemoteMediator
        )
    }
}