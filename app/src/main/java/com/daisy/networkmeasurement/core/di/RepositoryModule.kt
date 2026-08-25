package com.daisy.networkmeasurement.core.di

import com.daisy.networkmeasurement.core.config.data.repository.TestModeRepositoryImpl
import com.daisy.networkmeasurement.core.config.domain.repository.TestModeRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::TestModeRepositoryImpl) bind TestModeRepository::class
}