package com.minhky.core.data.repo

import com.minhky.core.data.executeRemote
import com.minhky.core.data.result.ResourceError
import com.minhky.core.data.result.ResourceResult
import com.minhky.core.network.service.UserService
import com.minhky.core.network.model.response.UserResponse
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getProfile(userName: String): ResourceResult<UserResponse, ResourceError> {
        return userService.getProfile(userName).executeRemote()
    }
}