package com.minhky.core.data.model

import com.minhky.core.database.model.UserEntity
import com.minhky.core.network.model.response.UserResponse

data class UserModel(
    val id: Long,
    val login: String,
    val avatarUrl: String? = null,
    val htmlUrl: String? = null,
    val location: String? = null,
    val followers: Long? = null,
    val following: Long? = null
) {
    fun toFollowers() = followers.toFollowString()

    fun toFollowing() = following.toFollowString()
}

fun Long?.toFollowString(): String {
    return when {
        this == null || this < 0 -> "0"
        10 >= this -> this.toString()
        10 < this && this <= 100 -> "10+"
        else -> "100+"
    }
}

fun UserModel.asUserEntity() = UserEntity(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = following
)

fun UserEntity.asUserModel() = UserModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = followers
)

fun UserResponse.asUserModel() = UserModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = following
)