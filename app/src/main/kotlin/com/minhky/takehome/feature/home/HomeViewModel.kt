package com.minhky.takehome.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.minhky.core.data.paging.UserPagingSource
import com.minhky.takehome.core.scope.AppDispatchers
import com.minhky.takehome.core.scope.Dispatcher
import com.minhky.takehome.ui.user.asUserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    userPagingSource: UserPagingSource,
    @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val userPaging = userPagingSource.paging.map {
        it.map {
            it.asUserUiModel()
        }
    }.flowOn(ioDispatcher).cachedIn(viewModelScope)

}
