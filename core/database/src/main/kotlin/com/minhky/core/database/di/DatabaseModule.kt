package com.minhky.core.database.di

import android.content.Context
import androidx.room.Room
import com.minhky.core.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module for providing database-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    /**
     * Provides a singleton instance of AppDatabase.
     *
     * @param context The application context used to create the database.
     * @return The AppDatabase instance.
     */
    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "take-home-database",
    ).build()
}