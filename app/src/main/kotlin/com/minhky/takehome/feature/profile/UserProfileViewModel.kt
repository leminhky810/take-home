package com.minhky.takehome.feature.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhky.core.domain.GetProfileUseCase
import com.minhky.takehome.core.result.Result
import com.minhky.takehome.ui.user.asUserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel for the user profile screen.
 *
 * @property loginUserName The username of the logged-in user, retrieved from the saved state handle.
 * @property uiState The state flow representing the UI state of the user profile screen.
 */
@HiltViewModel
class UserProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val loginUserName: String? = savedStateHandle.get<String>("loginUserName")

    /**
     * The UI state of the user profile screen, which is updated based on the result of the profile use case.
     */
    val uiState: StateFlow<UserProfileUiState> = getProfileUseCase(loginUserName).map {
        when (it) {
            is Result.Success -> UserProfileUiState.Success(it.data.asUserUiModel())
            is Result.Error -> UserProfileUiState.Error(it.data?.message ?: "Unknown error")
            Result.Loading -> UserProfileUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UserProfileUiState.Loading
    )
}