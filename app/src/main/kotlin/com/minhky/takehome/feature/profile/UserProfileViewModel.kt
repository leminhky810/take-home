package com.minhky.takehome.feature.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor() : ViewModel() {
    val uiState: StateFlow<UserProfileUiState> = MutableStateFlow(UserProfileUiState.Loading).asStateFlow()



}