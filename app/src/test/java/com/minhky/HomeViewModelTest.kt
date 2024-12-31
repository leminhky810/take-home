package com.minhky

import com.minhky.core.data.paging.UserPagingSource
import com.minhky.takehome.feature.home.HomeViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var userPagingSource: UserPagingSource
    private lateinit var ioDispatcher: CoroutineDispatcher
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        userPagingSource = mock(UserPagingSource::class.java)
        ioDispatcher = mock(CoroutineDispatcher::class.java)
        homeViewModel = HomeViewModel(userPagingSource, ioDispatcher)
    }

}