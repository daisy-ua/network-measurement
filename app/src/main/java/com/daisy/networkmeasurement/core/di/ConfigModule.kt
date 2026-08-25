package com.daisy.networkmeasurement.core.di

import org.koin.dsl.module

val configModule = module {
    includes(networkModule, storageModule, dataSourceModule, repositoryModule)
}