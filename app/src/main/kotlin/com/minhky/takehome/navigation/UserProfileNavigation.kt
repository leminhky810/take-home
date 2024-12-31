package com.minhky.takehome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.minhky.takehome.feature.profile.UserProfileRoute
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing the UserProfileRoute.
 *
 * @property loginUserName The username of the logged-in user.
 */
@Serializable
data class UserProfileRoute(val loginUserName: String)

/**
 * Extension function for NavController to navigate to the user profile screen.
 *
 * @param loginUserName The username of the logged-in user.
 */
fun NavController.navigateToUserProfile(loginUserName: String) = navigate(route = UserProfileRoute(loginUserName))

/**
 * Extension function for NavGraphBuilder to add the user profile screen composable.
 *
 * @param onBack Callback function to be invoked when the back action is triggered.
 * @param onShowSnackbar Callback function to show a snackbar with a message and an optional action.
 */
fun NavGraphBuilder.userProfileScreen(
    onBack: () -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable<UserProfileRoute> {
        UserProfileRoute(onBack, onShowSnackbar)
    }
}