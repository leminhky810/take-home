package com.minhky.takehome.feature.home

import com.minhky.takehome.ui.user.UserUiModel
import com.minhky.takehome.ui.user.fakeUserUiModel

/**
 * A sealed hierarchy describing the state of the users.
 */
sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(
        val users: List<UserUiModel>,
    ) : HomeUiState
}

private val loopTest = (0..100).map { item ->
    fakeUserUiModel.copy(
        id = item+1L
    )
}

val fakeHomeUiState = HomeUiState.Success(
    users = loopTest
)