package com.minhky.core.data.di

import com.minhky.core.data.paging.UserPagingSource
import com.minhky.core.data.paging.UserRemoteMediator
import com.minhky.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

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