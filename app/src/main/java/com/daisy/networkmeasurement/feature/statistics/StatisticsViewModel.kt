package com.daisy.networkmeasurement.feature.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StatisticsViewModel(
    getAllMeasurementsUseCase: GetAllMeasurementsUseCase
) : ViewModel() {
    val uiState: StateFlow<StatisticsUiState> =
        getAllMeasurementsUseCase()
            .map { measurements ->
                StatisticsUiState(
                    measurements = measurements,
                    isLoading = false
                )
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, StatisticsUiState())
}