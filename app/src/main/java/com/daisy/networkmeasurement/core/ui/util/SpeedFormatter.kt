package com.daisy.networkmeasurement.core.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.daisy.networkmeasurement.R

fun formatSpeedValue(
    mbps: Double,
): String = when {
    mbps >= 100 -> "%.0f".format(mbps)
    else -> "%.1f".format(mbps)
}

@Composable
fun formatSpeed(mbps: Double): String =
    "${formatSpeedValue(mbps)} ${stringResource(R.string.speed_unit_mbps)}"