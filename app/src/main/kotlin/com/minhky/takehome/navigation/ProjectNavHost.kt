package com.minhky.takehome.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.minhky.takehome.uiMain.ProjectAppState

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun ProjectNavHost(
    appState: ProjectAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        homeScreen(
            onUserProfileClick = navController::navigateToUserProfile,
            onShowSnackbar = onShowSnackbar,
        )
        userProfileScreen(
            onBack = navController::popBackStack,
            onShowSnackbar = onShowSnackbar,
        )
    }
}