package com.minhky.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.minhky.core.database.model.UserEntity

/**
 * Data Access Object (DAO) for accessing user data in the database.
 */
@Dao
interface UserDao {

    /**
     * Inserts a list of users into the database.
     * If a conflict occurs, the existing user will be replaced.
     *
     * @param users The list of users to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    /**
     * Deletes all users from the database.
     */
    @Query("DELETE FROM users")
    suspend fun clearAll()

    /**
     * Retrieves all users from the database.
     *
     * @return A PagingSource that provides data for pagination.
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): PagingSource<Int, UserEntity>

    /**
     * Retrieves a user by their login username.
     *
     * @param loginUserName The login username of the user to be retrieved.
     * @return The UserEntity corresponding to the given login username.
     */
    @Query("SELECT * FROM users WHERE login = :loginUserName")
    fun getUser(loginUserName: String): UserEntity

    /**
     * Updates the information of an existing user in the database.
     *
     * @param user The user entity with updated information.
     */
    @Update
    suspend fun updateUser(user: UserEntity)
}