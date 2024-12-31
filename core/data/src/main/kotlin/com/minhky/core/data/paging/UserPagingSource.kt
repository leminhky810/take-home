package com.minhky.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.minhky.core.data.model.asUserModel
import com.minhky.core.database.dao.UserDao
import kotlinx.coroutines.flow.map

/**
 * A PagingSource implementation for loading user data from the database and network.
 *
 * @property userDao The DAO for accessing user data.
 * @property userRemoteMediator The remote mediator for handling user data synchronization.
 */
class UserPagingSource(
    private val userDao: UserDao,
    userRemoteMediator: UserRemoteMediator,
) {

    /**
     * A Pager instance for managing the paging configuration and data flow.
     */
    @OptIn(ExperimentalPagingApi::class)
    val paging = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        remoteMediator = userRemoteMediator,
        pagingSourceFactory = {
            userDao.getAllUsers()
        }
    ).flow.map { pagingData ->
        pagingData.map { model ->
            model.asUserModel()
        }
    }

    companion object {
        /**
         * The size of each page to be loaded from the network.
         */
        const val NETWORK_PAGE_SIZE = 20
    }
}