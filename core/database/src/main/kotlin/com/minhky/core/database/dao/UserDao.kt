package com.minhky.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minhky.core.database.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Query("DELETE FROM users")
    suspend fun clearAll()

    @Query("SELECT * FROM users")
    fun getAllUsers(): PagingSource<Int, UserEntity>

    @Query("SELECT * FROM users WHERE login = :login")
    fun getUser(login: String): UserEntity

}