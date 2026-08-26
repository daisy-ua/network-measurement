package com.daisy.networkmeasurement.feature.test.speedtest.data.repository

import com.daisy.networkmeasurement.core.database.SpeedTestEntity
import com.daisy.networkmeasurement.feature.test.speedtest.data.remote.SpeedTestRemoteState
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestResult
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestStatus

fun SpeedTestRemoteState.toDomainStatus(): SpeedTestStatus {
    return when (this) {
        is SpeedTestRemoteState.Completed -> SpeedTestStatus.Completed(averageMbps, peakMbps)
        is SpeedTestRemoteState.Running -> SpeedTestStatus.Running(
            currentMbps = currentMbps,
            peakMbps = peakMbps,
            durationElapsed = durationElapsed
        )

        is SpeedTestRemoteState.Error -> SpeedTestStatus.Failed(
            cause = message ?: "Something went wrong"
        )
    }
}

fun SpeedTestResult.toEntity(): SpeedTestEntity =
    SpeedTestEntity(
        averageMbps = averageSpeedMbps,
        peakMbps = peakSpeedMbps,
        timestamp = measuredAt
    )

fun SpeedTestEntity.toDomain(): SpeedTestResult =
    SpeedTestResult(
        id = id,
        averageSpeedMbps = averageMbps,
        peakSpeedMbps = peakMbps,
        measuredAt = timestamp,
    )