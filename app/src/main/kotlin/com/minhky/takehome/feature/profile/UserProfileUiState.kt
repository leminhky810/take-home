package com.minhky.takehome.feature.profile

import com.minhky.takehome.ui.user.UserUiModel
import com.minhky.takehome.ui.user.fakeUserUiModel

/**
 * A sealed hierarchy describing the state of the profile of user.
 */
sealed interface UserProfileUiState {
    data object Loading : UserProfileUiState

    data object Error : UserProfileUiState

    data class Success(
        val data: UserUiModel,
    ) : UserProfileUiState
}

val fakeUserProfileUiState = UserProfileUiState.Success(fakeUserUiModel)