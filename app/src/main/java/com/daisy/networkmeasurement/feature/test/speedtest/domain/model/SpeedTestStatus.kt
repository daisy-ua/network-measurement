package com.daisy.networkmeasurement.feature.test.speedtest.domain.model

import kotlin.time.Clock
import kotlin.time.Duration

sealed interface SpeedTestStatus {
    data object Connecting : SpeedTestStatus

    data class Running(
        val currentMbps: Double,
        val peakMbps: Double,
        val durationElapsed: Duration
    ) : SpeedTestStatus

    data class Completed(
        val averageMbps: Double,
        val peakMbps: Double
    ) : SpeedTestStatus

    data class Failed(val cause: String) : SpeedTestStatus
}

fun SpeedTestStatus.Completed.toResult() = SpeedTestResult(
    averageSpeedMbps = averageMbps,
    peakSpeedMbps = peakMbps,
    measuredAt = Clock.System.now()
)