package com.minhky.takehome.feature.home

import com.minhky.takehome.ui.user.UserUiModel
import com.minhky.takehome.ui.user.fakeUserUiModel

/**
 * A sealed hierarchy describing the state of the users.
 */
sealed interface HomeUiState {
    /**
     * Represents the loading state of the home screen.
     */
    data object Loading : HomeUiState

    /**
     * Represents the success state of the home screen.
     *
     * @property users The list of user UI models.
     */
    data class Success(
        val users: List<UserUiModel>,
    ) : HomeUiState
}

/**
 * A list of fake user UI models for testing purposes.
 */
private val loopTest = (0..100).map { item ->
    fakeUserUiModel.copy(
        id = item + 1L
    )
}

/**
 * A fake home UI state for testing purposes.
 */
val fakeHomeUiState = HomeUiState.Success(
    users = loopTest
)