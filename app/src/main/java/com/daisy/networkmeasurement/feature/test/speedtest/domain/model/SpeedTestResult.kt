package com.daisy.networkmeasurement.feature.test.speedtest.domain.model

import kotlin.time.Instant

data class SpeedTestResult(
    val id: Long = 0,
    val averageSpeedMbps: Double,
    val peakSpeedMbps: Double,
    val measuredAt: Instant,
)