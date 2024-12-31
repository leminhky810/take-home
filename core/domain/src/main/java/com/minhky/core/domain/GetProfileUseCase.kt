package com.minhky.core.domain

import com.minhky.core.data.model.UserModel
import com.minhky.core.data.model.asUserModel
import com.minhky.core.data.mapper.asUserEntity
import com.minhky.core.data.repo.UserRepository
import com.minhky.core.data.result.ResourceResult
import com.minhky.core.database.dao.UserDao
import com.minhky.takehome.core.scope.AppDispatchers
import com.minhky.takehome.core.scope.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import com.minhky.takehome.core.result.Result
import kotlinx.coroutines.flow.onStart

/**
 * Use case for retrieving a user profile.
 *
 * @property userRepository The repository to fetch user data from remote sources.
 * @property userDao The DAO to access user data from the local database.
 * @property ioDispatcher The dispatcher for IO operations.
 */
class GetProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userDao: UserDao,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) {

    /**
     * Invokes the use case to get a user profile.
     *
     * @param loginUserName The login username of the user.
     * @return A Flow emitting the result of the user profile retrieval.
     */
    operator fun invoke(loginUserName: String?): Flow<Result<UserModel>> {
        return flow {

            if (loginUserName.isNullOrEmpty()) {
                emit(Result.Error(Throwable("Login username empty")))
                return@flow
            }

            // Check user from database
            val userLocal = userDao.getUser(loginUserName)
            if (userLocal.isSyncDetail) {
                emit(Result.Success(userLocal.asUserModel()))
                return@flow
            }

            // Get user from remote
            val userProfileRemote = userRepository.getProfile(loginUserName)

            if (userProfileRemote is ResourceResult.Success) {
                userProfileRemote.data?.let { user ->
                    userDao.updateUser(user.asUserEntity(isSyncDetail = true))
                    emit(Result.Success(user.asUserModel()))
                    return@flow
                }
            }

            emit(Result.Success(userLocal.asUserModel()))

        }.onStart {
            emit(Result.Loading)
        }.flowOn(ioDispatcher)
    }
}