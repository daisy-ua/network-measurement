package com.daisy.networkmeasurement.ui.app

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.daisy.networkmeasurement.AppState
import com.daisy.networkmeasurement.R
import com.daisy.networkmeasurement.core.config.domain.model.ConfigState
import com.daisy.networkmeasurement.navigation.AppNavHost
import org.koin.compose.koinInject

@Composable
fun AppScreen(appState: AppState = koinInject()) {
    val configState by appState.configState.collectAsStateWithLifecycle()

    AnimatedContent(targetState = configState, label = "app-ready-gate") { state ->
        when (state) {
            ConfigState.Ready -> AppContent()
            else -> LoadingScreen(state = state, onRetry = appState::refresh)
        }
    }
}

@Composable
fun AppContent(
    navController: NavHostController = rememberNavController()
) {
    val currentDestination = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination

    Scaffold(
        bottomBar = {
            AppBottomBar(
                currentDestination = currentDestination,
                onTabSelected = { tab ->
                    navController.navigateToTab(tab.route)
                },
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

fun NavHostController.navigateToTab(route: Any) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
private fun LoadingScreen(state: ConfigState, onRetry: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state) {
            ConfigState.Loading -> CircularProgressIndicator()
            ConfigState.Error -> {
                Button(onClick = onRetry) {
                    Text(text = stringResource(R.string.startup_label_retry))
                }
            }

            ConfigState.Ready -> Unit
        }
    }
}