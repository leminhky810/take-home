package com.minhky.core.network.model

sealed class NetworkErrorCode(val value: String) {
    data object IncorrectAuth: NetworkErrorCode("Auth-028")
}