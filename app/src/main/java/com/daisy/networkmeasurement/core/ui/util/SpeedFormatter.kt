package com.daisy.networkmeasurement.core.ui.util

fun formatSpeedValue(
    mbps: Double,
): String = when {
    mbps >= 100 -> "%.0f".format(mbps)
    else -> "%.1f".format(mbps)
}

fun formatSpeed(mbps: Double): String =
    "${formatSpeedValue(mbps)} Mbps"