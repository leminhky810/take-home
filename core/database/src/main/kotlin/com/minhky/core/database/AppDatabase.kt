package com.minhky.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.minhky.core.database.dao.UserDao
import com.minhky.core.database.model.UserEntity
import com.minhky.core.database.util.InstantConverter

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
    abstract fun userDao(): UserDao
}