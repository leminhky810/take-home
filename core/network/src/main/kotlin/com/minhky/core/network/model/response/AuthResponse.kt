package com.minhky.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val status: Int? = null,
    val type: String? = null,
    val userId: Int? = null
)