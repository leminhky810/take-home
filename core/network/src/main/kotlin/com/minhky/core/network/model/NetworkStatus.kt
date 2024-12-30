package com.minhky.core.network.model

sealed class NetworkStatus(vararg val code: Int) {
    data object Success : NetworkStatus(200)
    data object Created : NetworkStatus(201)
    data object BadRequest : NetworkStatus(400)
    data object UnAuthorized : NetworkStatus(401)
    data object NotFound : NetworkStatus(404)
    data object BadGateway : NetworkStatus(500, 501, 502)
}