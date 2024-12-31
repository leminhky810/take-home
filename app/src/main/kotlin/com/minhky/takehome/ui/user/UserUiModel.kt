package com.minhky.takehome.ui.user

import com.minhky.core.data.model.UserModel

data class UserUiModel(
    val id: Long,
    val login: String,
    val avatarUrl: String? = null,
    val htmlUrl: String? = null,
    val location: String? = null,
    val followers: String = "",
    val following: String = "",
)

fun UserModel.asUserUiModel(): UserUiModel {
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