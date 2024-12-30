package com.minhky.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.minhky.core.database.AppDatabase
import com.minhky.core.database.model.UserEntity
import com.minhky.core.network.service.UserService
import retrofit2.HttpException
import java.io.IOException
import androidx.room.withTransaction
import com.minhky.core.network.model.response.ErrorResponse
import com.minhky.core.network.model.response.UserResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(
    private val service: UserService,
    private val repoDatabase: AppDatabase,
    private val json: Json
) : RemoteMediator<Int, UserEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        0
                    } else {
                        lastItem.id
                    }
                }
            }

            val users = service.getList(
                page = loadKey,
                limit = state.config.pageSize
            ).execute()

            if (!users.isSuccessful) {
                val error = json.decodeFromString<ErrorResponse>(users.errorBody()!!.string())
                MediatorResult.Error(
                    IOException(error.message)
                )
            } else {

                val data = users.takeIf { it.isSuccessful }?.body() ?: emptyList()
                repoDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        repoDatabase.userDao().clearAll()
                    }
                    val entities = data.asUsersEntity()
                    repoDatabase.userDao().insertAll(entities)
                }

                MediatorResult.Success(
                    endOfPaginationReached = data.isEmpty()
                )
            }
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}

fun UserResponse.asUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl,
        location = location,
        followers = followers,
        following = following
    )
}

fun List<UserResponse>.asUsersEntity(): List<UserEntity> {
    return map {
        it.asUserEntity()
    }
}