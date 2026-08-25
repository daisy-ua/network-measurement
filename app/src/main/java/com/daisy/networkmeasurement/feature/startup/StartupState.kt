package com.daisy.networkmeasurement.feature.startup

import com.daisy.networkmeasurement.core.config.domain.model.TestMode

sealed interface StartupState {
    data object Loading : StartupState
    data object Error : StartupState
    data class Success (val testMode: TestMode) : StartupState
}