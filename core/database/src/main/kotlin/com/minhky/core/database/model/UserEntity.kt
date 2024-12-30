package com.minhky.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Long,
    val login: String? = null,
    val avatarUrl: String? = null,
    val htmlUrl: String? = null,
    val location: String? = null,
    val followers: Long? = null,
    val following: Long? = null,
    val isSyncDetail: Boolean = false
) {

    fun toFollowers() = followers.toFollowString()

    fun toFollowing() = following.toFollowString()
}


private fun Long?.toFollowString(): String {
    return when {
        this == null || this < 0 -> "0"
        10 >= this -> this.toString()
        10 < this && this <= 100 -> "10+"
        else -> "100+"
    }
}