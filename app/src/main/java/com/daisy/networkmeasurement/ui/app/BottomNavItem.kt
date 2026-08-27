package com.daisy.networkmeasurement.ui.app

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.daisy.networkmeasurement.R
import com.daisy.networkmeasurement.feature.statistics.navigation.StatisticsRoute
import com.daisy.networkmeasurement.feature.test.navigation.TestRoute

enum class BottomNavItem(
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int,
    val route: Any
) {
    TEST(
        titleId = R.string.main_test,
        iconId = R.drawable.ic_speed_24,
        route = TestRoute
    ),
    STATISTICS(
        titleId = R.string.main_statistics,
        iconId = R.drawable.ic_bar_chart_24,
        route = StatisticsRoute
    )
}