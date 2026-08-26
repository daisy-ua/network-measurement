package com.daisy.networkmeasurement.feature.test.speedtest.di

import com.daisy.networkmeasurement.feature.test.speedtest.data.remote.SpeedTestRemoteDataSource
import com.daisy.networkmeasurement.feature.test.speedtest.data.remote.SpeedTestRemoteDataSourceImpl
import com.daisy.networkmeasurement.feature.test.speedtest.data.repository.SpeedTestRepositoryImpl
import com.daisy.networkmeasurement.feature.test.speedtest.domain.repository.SpeedTestRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val speedTestModule = module {
    singleOf(::SpeedTestRemoteDataSourceImpl) bind SpeedTestRemoteDataSource::class
    singleOf(::SpeedTestRepositoryImpl) bind SpeedTestRepository::class
}