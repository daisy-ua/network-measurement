package com.daisy.networkmeasurement.feature.statistics

import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestResult
import com.daisy.networkmeasurement.feature.test.speedtest.domain.repository.SpeedTestRepository
import kotlinx.coroutines.flow.Flow

class GetAllMeasurements(
    private val repository: SpeedTestRepository
) {
    operator fun invoke(): Flow<List<SpeedTestResult>> = repository.getSavedResults()
}