package com.minhky.core.database.di

import com.minhky.core.database.AppDatabase
import com.minhky.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesUserDao(
        database: AppDatabase,
    ): UserDao = database.userDao()

}