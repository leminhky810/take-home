package com.minhky.core.data.result

typealias RootError = Error

sealed interface ResourceResult<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D?) : ResourceResult<D, E>
    data class Error<out D, out E: RootError>(val error: E) : ResourceResult<D, E>
}
