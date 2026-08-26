package com.daisy.networkmeasurement.feature.test.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daisy.networkmeasurement.feature.test.TestScreen
import kotlinx.serialization.Serializable

@Serializable
data object MainRoute

@Serializable
data object TestRoute

fun NavGraphBuilder.testScreen() {
    composable<TestRoute> { TestScreen() }
}
