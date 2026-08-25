package com.daisy.networkmeasurement.core.config.data.repository

import com.daisy.networkmeasurement.core.config.data.local.TestModeLocalDataSource
import com.daisy.networkmeasurement.core.config.data.local.toTestMode
import com.daisy.networkmeasurement.core.config.data.remote.TestModeRemoteDataSource
import com.daisy.networkmeasurement.core.config.domain.model.TestMode
import com.daisy.networkmeasurement.core.config.domain.repository.TestModeRepository
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.flow.firstOrNull

class TestModeRepositoryImpl(
    private val localDataSource: TestModeLocalDataSource,
    private val remoteDataSource: TestModeRemoteDataSource,
) : TestModeRepository {
    override suspend fun getTestMode(): Result<TestMode> {
        return try {
            val cachedMode = localDataSource.getTestMode().firstOrNull()

            if (cachedMode != null) {
                Result.success(cachedMode)
            } else {
                val config = remoteDataSource.getRemoteConfig()
                val testMode = config?.mode.toTestMode()

                localDataSource.saveTestMode(testMode)

                Result.success(testMode)
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}