package com.minhky.takehome.ui.user

import com.minhky.core.database.model.UserEntity

data class UserUiModel(
    val id: Long,
    val login: String? = null,
    val avatarUrl: String? = null,
    val htmlUrl: String? = null,
    val location: String? = null,
    val followers: String = "",
    val following: String = "",
)

fun UserEntity.asUserUiModel(): UserUiModel {
    return UserUiModel(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl,
        location = location,
        followers = toFollowers(),
        following = toFollowing()
    )
}

val fakeUserUiModel = UserUiModel(
    id = 583231,
    login = "octocat",
    avatarUrl = "https://avatars.githubusercontent.com/u/583231?v=4",
    location = "San Francisco",
    htmlUrl = "https://github.com/jvantuyl",
    followers = "10+",
    following = "100+",
)