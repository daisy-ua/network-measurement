package com.daisy.networkmeasurement.feature.main.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.daisy.networkmeasurement.core.config.domain.model.TestMode
import kotlinx.serialization.Serializable

@Serializable
data class MainRoute(val testMode: TestMode)

fun NavController.navigateToMain(testMode: TestMode, navOptions: NavOptions? = null) =
    navigate(MainRoute(testMode), navOptions)

fun NavGraphBuilder.mainScreen() {
    composable<MainRoute>() { backStackEntry ->
        Text(
            text = "Main route ${backStackEntry.toRoute<MainRoute>().testMode}"
        )
    }
}