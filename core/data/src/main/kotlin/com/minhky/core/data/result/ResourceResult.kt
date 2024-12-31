package com.minhky.core.data.result

/**
 * Type alias for the root error type.
 */
typealias RootError = Error

/**
 * A sealed interface representing the result of a resource operation.
 *
 * @param D The type of the data in case of success.
 * @param E The type of the error in case of failure, which must extend RootError.
 */
sealed interface ResourceResult<out D, out E: RootError> {

    /**
     * A data class representing a successful resource operation.
     *
     * @param data The data returned by the operation, which may be null.
     */
    data class Success<out D, out E: RootError>(val data: D?) : ResourceResult<D, E>

    /**
     * A data class representing a failed resource operation.
     *
     * @param error The error returned by the operation.
     */
    data class Error<out D, out E: RootError>(val error: E) : ResourceResult<D, E>
}