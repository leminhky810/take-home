package com.minhky.core.data.result

import com.minhky.core.network.model.response.ErrorResponse

sealed interface ResourceError: Error {

    sealed interface RemoteError: ResourceError {

        data class RemoteDataError(val error: ErrorResponse) : ResourceError

        enum class RemoteException: ResourceError {
            UnAuthorized,
            BadRequest,
            BadGateway,
            ResponseEmpty,
            ConnectError
        }
    }

    enum class LocalError: ResourceError {
        FullDisk
    }

    data object UnknownError: ResourceError

}
