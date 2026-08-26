package com.daisy.networkmeasurement.feature.test.speedtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestStatus
import com.daisy.networkmeasurement.feature.test.speedtest.domain.usecase.RunSpeedTestUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpeedTestViewModel(
    private val runSpeedTestUseCase: RunSpeedTestUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<SpeedTestUiState> =
        MutableStateFlow(SpeedTestUiState.Idle)
    val uiState: StateFlow<SpeedTestUiState> = _uiState.asStateFlow()

    private var testJob: Job? = null

    fun start() {
        testJob?.cancel()
        testJob = viewModelScope.launch {
            runSpeedTestUseCase()
                .collect { state ->
                    when (state) {
                        is SpeedTestStatus.Completed -> {
                            _uiState.value = SpeedTestUiState.Completed(
                                averageMbps = state.averageMbps,
                                peakMbps = state.peakMbps
                            )
                        }

                        is SpeedTestStatus.Failed -> {
                            _uiState.value = SpeedTestUiState.Error(state.cause)
                        }

                        is SpeedTestStatus.Running -> {
                            _uiState.value = SpeedTestUiState.Running(
                                elapsedMillis = state.durationElapsed.inWholeMilliseconds,
                                currentMbps = state.currentMbps,
                                peakMbps = state.peakMbps
                            )
                        }
                    }
                }
        }
    }

    fun stop() {
        testJob?.cancel()
        testJob = null
    }
}