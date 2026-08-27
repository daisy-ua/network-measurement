package com.daisy.networkmeasurement.feature.test.speedtest.ui

sealed interface SpeedTestUiState {

    data object Idle : SpeedTestUiState

    data object Connecting : SpeedTestUiState

    data class Running(
        val currentMbps: Double,
        val peakMbps: Double,
    ) : SpeedTestUiState

    data class Completed(
        val averageMbps: Double,
        val peakMbps: Double,
    ) : SpeedTestUiState

    data class Error(
        val message: String,
    ) : SpeedTestUiState
}