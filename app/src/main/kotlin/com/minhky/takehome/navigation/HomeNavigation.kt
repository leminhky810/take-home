package com.minhky.takehome.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.minhky.takehome.feature.home.HomeRoute
import kotlinx.serialization.Serializable

/**
 * Serializable object representing the HomeRoute.
 */
@Serializable object HomeRoute

/**
 * Extension function for NavGraphBuilder to add the home screen composable.
 *
 * @param onUserProfileClick Callback function to be invoked when the user profile is clicked.
 * @param onShowSnackbar Callback function to show a snackbar with a message and an optional action.
 */
fun NavGraphBuilder.homeScreen(
    onUserProfileClick: (String) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable<HomeRoute> {
        HomeRoute(onUserProfileClick, onShowSnackbar)
    }
}