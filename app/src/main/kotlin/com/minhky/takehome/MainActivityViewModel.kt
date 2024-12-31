package com.minhky.takehome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhky.core.model.AppSession
import com.minhky.core.preferences.AppSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel for the MainActivity.
 *
 * @property uiState The state of the UI, represented as a StateFlow of MainActivityUiState.
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    appSharedPreferences: AppSharedPreferences
) : ViewModel() {

    /**
     * The UI state of the MainActivity.
     * It retrieves the user session from the shared preferences and maps it to a Success state after a delay.
     */
    val uiState: StateFlow<MainActivityUiState> = appSharedPreferences.getUserSession().map {
        delay(1000L)
        MainActivityUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

}

/**
 * Represents the different states of the MainActivity UI.
 */
sealed interface MainActivityUiState {
    /**
     * Represents the loading state of the UI.
     */
    data object Loading : MainActivityUiState

    /**
     * Represents the success state of the UI with the user session data.
     *
     * @property useSession The user session data.
     */
    data class Success(val useSession: AppSession) : MainActivityUiState

    /**
     * Returns `true` if the state wasn't loaded yet and it should keep showing the splash screen.
     */
    fun shouldKeepSplashScreen() = this is Loading

    /**
     * Returns `true` if dark theme should be used.
     *
     * @param isSystemDarkTheme Indicates if the system is using dark theme.
     */
    fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) = isSystemDarkTheme
}