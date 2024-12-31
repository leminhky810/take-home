package com.minhky.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.minhky.core.database.dao.UserDao
import com.minhky.core.database.model.UserEntity
import com.minhky.core.database.util.InstantConverter

/**
 * The main database for the application.
 *
 * @property userDao Provides access to user-related database operations.
 */
@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    autoMigrations = [

    ],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Abstract method to get the UserDao.
     *
     * @return The UserDao instance.
     */
    abstract fun userDao(): UserDao
}