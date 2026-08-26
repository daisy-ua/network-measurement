package com.daisy.networkmeasurement.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.daisy.networkmeasurement.feature.statistics.navigation.statisticsScreen
import com.daisy.networkmeasurement.feature.test.navigation.MainRoute
import com.daisy.networkmeasurement.feature.test.navigation.TestRoute
import com.daisy.networkmeasurement.feature.test.navigation.testScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute,
        modifier = modifier
    ) {
        navigation<MainRoute>(startDestination = TestRoute) {
            testScreen()
            statisticsScreen()
        }
    }
}