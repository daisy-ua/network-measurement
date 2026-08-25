package com.daisy.networkmeasurement.feature.startup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daisy.networkmeasurement.core.config.domain.model.TestMode
import com.daisy.networkmeasurement.feature.startup.StartupScreen
import kotlinx.serialization.Serializable

@Serializable
data object StartupRoute

fun NavGraphBuilder.startupScreen(
    onTestModeLoaded: (TestMode) -> Unit,
) {
    composable<StartupRoute> {
        StartupScreen(
            onTestModeLoaded = onTestModeLoaded
        )
    }
}