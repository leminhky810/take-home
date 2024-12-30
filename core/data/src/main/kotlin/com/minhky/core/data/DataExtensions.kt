package com.minhky.core.data

import com.minhky.core.data.result.ResourceError
import com.minhky.core.data.result.ResourceResult
import com.minhky.core.network.model.NetworkStatus
import com.minhky.core.network.model.response.ErrorResponse
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Response
import java.net.ConnectException

fun <DATA> Call<DATA>.executeRemote(): ResourceResult<DATA, ResourceError> {
    return try {
        val execute = execute()
        if (execute.isSuccessful) {
            ResourceResult.Success(execute.body())
        } else {
            ResourceResult.Error(handleRemoteError(execute))
        }
    } catch (ex: Throwable) {
        ResourceResult.Error(handleException(ex))
    }
}

private val json = Json {
    ignoreUnknownKeys = true
}

private fun <DATA> handleRemoteError(error: Response<DATA>): ResourceError {
    return when (error.code()) {
        in NetworkStatus.UnAuthorized.code -> {
            ResourceError.RemoteError.RemoteException.UnAuthorized
        }
        in NetworkStatus.BadGateway.code -> {
            ResourceError.RemoteError.RemoteException.BadGateway
        }
        in NetworkStatus.BadRequest.code -> {
            try {
                error.errorBody()?.string()?.let {
                    val e = json.decodeFromString<ErrorResponse>(it)
                    ResourceError.RemoteError.RemoteDataError(e)
                } ?: ResourceError.RemoteError.RemoteException.ResponseEmpty
            } catch (ex: Throwable) {
                ResourceError.UnknownError
            }
        }
        else -> {
            ResourceError.UnknownError
        }
    }
}

private fun handleException(error: Throwable): ResourceError {
    return when (error) {
        is ConnectException -> {
            ResourceError.RemoteError.RemoteException.ConnectError
        }
        else -> ResourceError.UnknownError
    }
}