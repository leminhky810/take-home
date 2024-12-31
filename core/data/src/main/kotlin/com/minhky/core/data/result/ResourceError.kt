package com.minhky.core.data.result

import com.minhky.core.network.model.response.ErrorResponse

/**
 * A sealed interface representing different types of resource errors.
 */
sealed interface ResourceError: Error {

    /**
     * A sealed interface representing remote errors.
     */
    sealed interface RemoteError: ResourceError {

        /**
         * A data class representing a remote data error.
         *
         * @property error The error response from the network.
         */
        data class RemoteDataError(val error: ErrorResponse) : ResourceError

        /**
         * An enum class representing different types of remote exceptions.
         */
        enum class RemoteException: ResourceError {
            UnAuthorized,
            BadRequest,
            BadGateway,
            ResponseEmpty,
            ConnectError
        }
    }

    /**
     * An enum class representing different types of local errors.
     */
    enum class LocalError: ResourceError {
        FullDisk
    }

    /**
     * A data object representing an unknown error.
     */
    data object UnknownError: ResourceError

}