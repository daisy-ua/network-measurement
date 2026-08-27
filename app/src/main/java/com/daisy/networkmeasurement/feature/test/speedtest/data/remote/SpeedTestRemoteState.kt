package com.daisy.networkmeasurement.feature.test.speedtest.data.remote

import kotlin.time.Duration

sealed interface SpeedTestRemoteState {
    data object Connecting : SpeedTestRemoteState

    data class Running(
        val currentMbps: Double,
        val peakMbps: Double,
        val durationElapsed: Duration
    ) : SpeedTestRemoteState

    data class Completed(
        val averageMbps: Double,
        val peakMbps: Double
    ) : SpeedTestRemoteState

    data class Error(
        val code: Int?,
        val message: String?,
    ) : SpeedTestRemoteState
}