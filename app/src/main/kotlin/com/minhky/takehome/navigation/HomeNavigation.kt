package com.minhky.takehome.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.minhky.takehome.feature.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable object HomeRoute

fun NavGraphBuilder.homeScreen(
    onUserProfileClick: (Long) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable<HomeRoute> {
        HomeRoute(onUserProfileClick, onShowSnackbar)
    }
}
