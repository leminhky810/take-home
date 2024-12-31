package com.minhky.core.data.repo

import com.minhky.core.data.executeRemote
import com.minhky.core.data.result.ResourceError
import com.minhky.core.data.result.ResourceResult
import com.minhky.core.network.service.UserService
import com.minhky.core.network.model.response.UserResponse
import javax.inject.Inject

/**
 * Implementation of the UserRepository interface for accessing user data.
 *
 * @property userService The UserService for making network requests.
 */
class UserRepositoryImp @Inject constructor(
    private val userService: UserService
) : UserRepository {

    /**
     * Retrieves the profile of a user by their username.
     *
     * @param userName The username of the user whose profile is to be retrieved.
     * @return A ResourceResult containing either the UserResponse or a ResourceError.
     */
    override suspend fun getProfile(userName: String): ResourceResult<UserResponse, ResourceError> {
        return userService.getProfile(userName).executeRemote()
    }
}