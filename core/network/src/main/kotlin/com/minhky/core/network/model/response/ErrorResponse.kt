package com.minhky.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String? = null,
    @SerialName("documentation_url")
    val documentationUrl: String? = null,
    val status: String? = null
)