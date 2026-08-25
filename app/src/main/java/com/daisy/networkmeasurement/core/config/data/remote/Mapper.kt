package com.daisy.networkmeasurement.core.config.data.remote

import com.daisy.networkmeasurement.core.config.domain.model.TestMode

fun TestModeDto.toTestMode() = when (this.mode) {
    "speed_test" -> TestMode.SPEED_TEST
    "ping_test" -> TestMode.PING_TEST
    else -> TestMode.DEFAULT
}