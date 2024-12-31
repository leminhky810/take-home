package com.minhky.core.data.mapper

import com.minhky.core.database.model.UserEntity
import com.minhky.core.network.model.response.UserResponse

/**
 * Extension function to map a UserResponse to a UserEntity.
 *
 * @param isSyncDetail A boolean flag indicating whether the user details are synchronized.
 * @return A UserEntity instance.
 */
fun UserResponse.asUserEntity(isSyncDetail: Boolean = false) = UserEntity(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = following,
    isSyncDetail = isSyncDetail
)

/**
 * Extension function to map a list of UserResponse objects to a list of UserEntity objects.
 *
 * @return A list of UserEntity instances.
 */
fun List<UserResponse>.asUsersEntity(): List<UserEntity> {
    return map {
        it.asUserEntity()
    }
}