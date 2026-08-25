package com.daisy.networkmeasurement.feature.startup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daisy.networkmeasurement.core.config.domain.repository.TestModeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartupViewModel(
    private val testModeRepository: TestModeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<StartupState>(StartupState.Loading)
    val uiState: StateFlow<StartupState> = _uiState.asStateFlow()

    init {
        loadTestMode()
    }

    fun loadTestMode() = viewModelScope.launch {
        _uiState.value = StartupState.Loading

        val result = testModeRepository.getTestMode()
        result
            .onSuccess {
                _uiState.value = StartupState.Success(it)
            }
            .onFailure {
                _uiState.value = StartupState.Error
            }
    }
}