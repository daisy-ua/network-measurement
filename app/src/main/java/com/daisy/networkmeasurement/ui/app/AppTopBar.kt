package com.daisy.networkmeasurement.ui.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import com.daisy.networkmeasurement.feature.statistics.components.StatisticsTopBar
import com.daisy.networkmeasurement.feature.statistics.navigation.StatisticsRoute
import kotlin.reflect.KClass

@Composable
fun AppTopBar(
    destination: NavDestination?,
) {
    when {
        destination?.isRoute(StatisticsRoute::class) == true -> {
            StatisticsTopBar()
        }
    }
}

fun NavDestination.isRoute(destinationRoute: KClass<*>) =
    this.route == destinationRoute.qualifiedName