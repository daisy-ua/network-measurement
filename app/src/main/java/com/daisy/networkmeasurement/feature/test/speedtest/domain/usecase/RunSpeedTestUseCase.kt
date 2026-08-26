package com.daisy.networkmeasurement.feature.test.speedtest.domain.usecase

import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestStatus
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.toResult
import com.daisy.networkmeasurement.feature.test.speedtest.domain.repository.SpeedTestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach

class RunSpeedTestUseCase(
    private val repository: SpeedTestRepository
) {
    operator fun invoke(): Flow<SpeedTestStatus> {
        return repository.runDownloadSpeedTest()
            .onEach { status ->
                if (status is SpeedTestStatus.Completed) {
                    repository.saveSpeedTestResult(status.toResult())
                }
            }
            .catch { _ ->
                emit(SpeedTestStatus.Failed(cause = "Unknown error"))
            }
    }
}