package com.daisy.networkmeasurement.core.config.domain.model

sealed interface ConfigState {
    data object Loading : ConfigState
    data object Ready : ConfigState
    data object Error : ConfigState
}
