package com.minhky.takehome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.minhky.takehome.feature.profile.UserProfileRoute
import kotlinx.serialization.Serializable

@Serializable data class UserProfileRoute(val userId: Long)

fun NavController.navigateToUserProfile(userId: Long) = navigate(route = UserProfileRoute(userId))

fun NavGraphBuilder.userProfileScreen(
    onBack: () -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable<UserProfileRoute> {
        UserProfileRoute(onBack, onShowSnackbar)
    }
}
