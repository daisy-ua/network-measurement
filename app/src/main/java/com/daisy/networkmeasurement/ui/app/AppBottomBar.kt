package com.daisy.networkmeasurement.ui.app

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.daisy.networkmeasurement.feature.statistics.navigation.StatisticsRoute
import com.daisy.networkmeasurement.feature.test.navigation.TestRoute

@Composable
fun AppBottomBar(
    currentDestination: NavDestination?,
    onTabSelected: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        BottomNavItem.entries.forEach { tab ->
            val isSelected = currentDestination?.matches(tab) == true

            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        painter = painterResource(tab.iconId),
                        contentDescription = stringResource(tab.titleId),
                    )
                },
                label = {
                    Text(
                        text = stringResource(tab.titleId)
                    )
                }
            )
        }
    }
}

private fun NavDestination.matches(
    tab: BottomNavItem,
): Boolean {
    return when (tab) {
        BottomNavItem.TEST ->
            hasRoute<TestRoute>()

        BottomNavItem.STATISTICS ->
            hasRoute<StatisticsRoute>()
    }
}