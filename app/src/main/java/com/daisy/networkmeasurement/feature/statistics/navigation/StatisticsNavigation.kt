package com.daisy.networkmeasurement.feature.statistics.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daisy.networkmeasurement.feature.statistics.StatisticsScreen
import kotlinx.serialization.Serializable

@Serializable
data object StatisticsRoute


fun NavGraphBuilder.statisticsScreen() {
    composable<StatisticsRoute> { StatisticsScreen() }
}