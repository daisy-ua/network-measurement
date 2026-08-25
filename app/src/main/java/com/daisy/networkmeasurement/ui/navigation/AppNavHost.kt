package com.daisy.networkmeasurement.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.daisy.networkmeasurement.feature.main.navigation.mainScreen
import com.daisy.networkmeasurement.feature.main.navigation.navigateToMain
import com.daisy.networkmeasurement.feature.startup.navigation.StartupRoute
import com.daisy.networkmeasurement.feature.startup.navigation.startupScreen
import androidx.navigation.NavDestination.Companion.hasRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = StartupRoute
    ) {
        startupScreen(
            onTestModeLoaded = { mode ->
                if (navController.currentDestination?.hasRoute<StartupRoute>() != true) {
                    return@startupScreen
                }
                navController.navigateToMain(
                    testMode = mode,
                    navOptions = navOptions {
                        popUpTo<StartupRoute> {
                            inclusive = true
                        }
                    }
                )
            }
        )

        mainScreen()
    }
}