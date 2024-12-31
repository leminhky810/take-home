package com.minhky.core.database.di

import com.minhky.core.database.AppDatabase
import com.minhky.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger module for providing DAO instances.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    /**
     * Provides an instance of UserDao.
     *
     * @param database The AppDatabase instance used to get the UserDao.
     * @return The UserDao instance.
     */
    @Provides
    fun providesUserDao(
        database: AppDatabase,
    ): UserDao = database.userDao()

}