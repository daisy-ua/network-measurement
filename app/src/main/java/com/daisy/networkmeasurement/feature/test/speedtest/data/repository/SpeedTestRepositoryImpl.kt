package com.daisy.networkmeasurement.feature.test.speedtest.data.repository

import com.daisy.networkmeasurement.core.database.SpeedTestDao
import com.daisy.networkmeasurement.core.database.SpeedTestEntity
import com.daisy.networkmeasurement.feature.test.speedtest.data.remote.SpeedTestRemoteDataSource
import com.daisy.networkmeasurement.feature.test.speedtest.data.remote.SpeedTestRemoteState
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestResult
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestStatus
import com.daisy.networkmeasurement.feature.test.speedtest.domain.repository.SpeedTestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlin.time.Duration

class SpeedTestRepositoryImpl(
    private val remoteDataSource: SpeedTestRemoteDataSource,
    private val localDataSource: SpeedTestDao
) : SpeedTestRepository {
    override fun runDownloadSpeedTest(targetDuration: Duration): Flow<SpeedTestStatus> =
        remoteDataSource.measureDownloadSpeed(targetDuration)
            .map(SpeedTestRemoteState::toDomainStatus)
            .flowOn(Dispatchers.IO)

    override suspend fun saveSpeedTestResult(speedTestResult: SpeedTestResult) {
        localDataSource.insert(speedTestResult.toEntity())
    }

    override fun getSavedResults(): Flow<List<SpeedTestResult>> {
        return localDataSource.getAllResults()
            .map { entities -> entities.map(SpeedTestEntity::toDomain) }
    }
}