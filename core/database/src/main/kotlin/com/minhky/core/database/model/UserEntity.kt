package com.minhky.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user entity in the database.
 *
 * @property id The unique identifier of the user.
 * @property login The login username of the user.
 * @property avatarUrl The URL of the user's avatar image.
 * @property htmlUrl The URL of the user's profile page.
 * @property location The location of the user.
 * @property followers The number of followers the user has.
 * @property following The number of users the user is following.
 * @property isSyncDetail Indicates whether the user's details are synchronized.
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Long,
    val login: String,
    val avatarUrl: String? = null,
    val htmlUrl: String? = null,
    val location: String? = null,
    val followers: Long? = null,
    val following: Long? = null,
    val isSyncDetail: Boolean = false
)