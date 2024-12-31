package com.minhky.takehome.uiMain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.minhky.core.data.util.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Remembers and creates an instance of [ProjectAppState].
 *
 * @param networkMonitor The network monitor to observe network status.
 * @param coroutineScope The coroutine scope for launching coroutines.
 * @param navController The navigation controller for managing navigation.
 * @return An instance of [ProjectAppState].
 */
@Composable
fun rememberProjectAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): ProjectAppState {
    return remember(
        navController,
        coroutineScope,
        networkMonitor,
    ) {
        ProjectAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            networkMonitor = networkMonitor,
        )
    }
}

/**
 * A state holder class for the Project app.
 *
 * @property navController The navigation controller for managing navigation.
 * @property coroutineScope The coroutine scope for launching coroutines.
 * @property networkMonitor The network monitor to observe network status.
 */
@Stable
class ProjectAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    /**
     * The current destination in the navigation stack.
     * Falls back to the previous destination if the current entry is null.
     */
    val currentDestination: NavDestination?
        @Composable get() {
            // Collect the currentBackStackEntryFlow as a state
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            // Fallback to previousDestination if currentEntry is null
            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    /**
     * A state flow that indicates whether the app is offline.
     * It maps the network monitor's online status to its negation.
     */
    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )
}