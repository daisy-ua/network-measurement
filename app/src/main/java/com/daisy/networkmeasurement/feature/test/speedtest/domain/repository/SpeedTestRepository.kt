package com.daisy.networkmeasurement.feature.test.speedtest.domain.repository

import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestResult
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestStatus
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

interface SpeedTestRepository {
    fun runDownloadSpeedTest(targetDuration: Duration = 10.seconds) : Flow<SpeedTestStatus>

    suspend fun saveSpeedTestResult(speedTestResult: SpeedTestResult)

    fun getSavedResults(): Flow<List<SpeedTestResult>>
}