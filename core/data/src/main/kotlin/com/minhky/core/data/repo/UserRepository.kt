package com.minhky.core.data.repo

import com.minhky.core.data.result.ResourceError
import com.minhky.core.data.result.ResourceResult
import com.minhky.core.network.model.response.UserResponse

/**
 * Interface for the UserRepository, which defines methods for accessing user data.
 */
interface UserRepository {
    /**
     * Retrieves the profile of a user by their username.
     *
     * @param userName The username of the user whose profile is to be retrieved.
     * @return A ResourceResult containing either the UserResponse or a ResourceError.
     */
    suspend fun getProfile(
        userName: String
    ): ResourceResult<UserResponse, ResourceError>
}