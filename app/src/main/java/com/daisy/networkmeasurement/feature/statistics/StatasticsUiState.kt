package com.daisy.networkmeasurement.feature.statistics

import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestResult

data class StatisticsUiState(
    val measurements: List<SpeedTestResult> = emptyList(),
    val isLoading: Boolean = true,
)