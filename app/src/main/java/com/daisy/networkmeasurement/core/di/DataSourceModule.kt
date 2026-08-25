package com.daisy.networkmeasurement.core.di

import com.daisy.networkmeasurement.core.config.data.local.TestModeLocalDataSource
import com.daisy.networkmeasurement.core.config.data.remote.TestModeRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataSourceModule = module {
    singleOf(::TestModeLocalDataSource)
    singleOf(::TestModeRemoteDataSource)
}