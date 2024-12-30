package com.minhky.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.minhky.core.database.dao.UserDao

class UserPagingSource(
    private val userDao: UserDao,
    private val userRemoteMediator: UserRemoteMediator,
) {

    @OptIn(ExperimentalPagingApi::class)
    val paging = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        remoteMediator = userRemoteMediator,
        pagingSourceFactory = {
            userDao.getAllUsers()
        }
    ).flow

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}

