package com.daisy.networkmeasurement.core.config.domain.repository

import com.daisy.networkmeasurement.core.config.domain.model.TestMode

interface TestModeRepository {
    suspend fun getTestMode(): Result<TestMode>
}