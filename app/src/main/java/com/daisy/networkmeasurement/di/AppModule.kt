package com.daisy.networkmeasurement.di

import com.daisy.networkmeasurement.AppState
import com.daisy.networkmeasurement.feature.statistics.GetAllMeasurementsUseCase
import com.daisy.networkmeasurement.feature.statistics.StatisticsViewModel
import com.daisy.networkmeasurement.feature.test.speedtest.domain.usecase.RunSpeedTestUseCase
import com.daisy.networkmeasurement.feature.test.speedtest.ui.SpeedTestViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { CoroutineScope(SupervisorJob() + Dispatchers.Default) }

    singleOf(::AppState)

    singleOf(::RunSpeedTestUseCase)
    singleOf(::GetAllMeasurementsUseCase)

    viewModelOf(::SpeedTestViewModel)
    viewModelOf(::StatisticsViewModel)
}