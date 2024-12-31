package com.minhky.takehome.feature.profile

import com.minhky.takehome.ui.user.UserUiModel
import com.minhky.takehome.ui.user.fakeUserUiModel

/**
 * A sealed hierarchy describing the state of the profile of user.
 */
sealed interface UserProfileUiState {
    /**
     * Represents the loading state of the user profile.
     */
    data object Loading : UserProfileUiState

    /**
     * Represents the error state of the user profile.
     *
     * @property message The error message to be displayed.
     */
    data class Error(val message: String) : UserProfileUiState

    /**
     * Represents the success state of the user profile.
     *
     * @property data The user profile data.
     */
    data class Success(
        val data: UserUiModel,
    ) : UserProfileUiState
}

/**
 * A fake user profile UI state for testing purposes.
 */
val fakeUserProfileUiState = UserProfileUiState.Success(fakeUserUiModel)