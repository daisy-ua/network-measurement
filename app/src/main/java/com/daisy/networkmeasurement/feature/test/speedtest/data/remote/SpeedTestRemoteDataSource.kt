package com.daisy.networkmeasurement.feature.test.speedtest.data.remote

import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

interface SpeedTestRemoteDataSource {
    fun measureDownloadSpeed(targetDuration: Duration): Flow<SpeedTestRemoteState>
}