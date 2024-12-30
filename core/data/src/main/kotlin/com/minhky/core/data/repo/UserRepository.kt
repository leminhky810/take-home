package com.minhky.core.data.repo

import com.minhky.core.data.result.ResourceError
import com.minhky.core.data.result.ResourceResult
import com.minhky.core.network.model.response.UserResponse

interface UserRepository {
    suspend fun getProfile(
        userName: String
    ): ResourceResult<UserResponse, ResourceError>
}