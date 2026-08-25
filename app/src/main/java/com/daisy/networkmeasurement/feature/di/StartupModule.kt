package com.daisy.networkmeasurement.feature.di

import com.daisy.networkmeasurement.feature.startup.StartupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val startupModule = module {
    viewModelOf(::StartupViewModel)
}