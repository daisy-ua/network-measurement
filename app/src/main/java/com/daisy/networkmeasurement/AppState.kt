package com.daisy.networkmeasurement

import com.daisy.networkmeasurement.core.config.domain.model.ConfigState
import com.daisy.networkmeasurement.core.config.domain.model.TestMode
import com.daisy.networkmeasurement.core.config.domain.repository.TestModeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppState(
    val coroutineScope: CoroutineScope,
    private val testModeRepository: TestModeRepository,
) {
    private val _testMode = MutableStateFlow<TestMode?>(null)
    val testMode: StateFlow<TestMode?> = _testMode.asStateFlow()

    private val _configState = MutableStateFlow<ConfigState>(ConfigState.Loading)
    val configState: StateFlow<ConfigState> = _configState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        coroutineScope.launch {
            _configState.value = ConfigState.Loading
            testModeRepository.getTestMode()
                .onSuccess { mode ->
                    _testMode.value = mode
                    _configState.value = ConfigState.Ready
                }
                .onFailure {
                    _configState.value = ConfigState.Error
                }
        }
    }
}