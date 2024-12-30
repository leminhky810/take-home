package com.minhky.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(kotlinx.serialization.InternalSerializationApi::class)
@Serializable
data class UserResponse(
    val id: Long,
    val login: String? = null,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("html_url")
    val htmlUrl: String? = null,
    val location: String? = null,
    val followers: Long? = null,
    val following: Long? = null
)