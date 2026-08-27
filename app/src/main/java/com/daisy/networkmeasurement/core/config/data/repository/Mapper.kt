package com.daisy.networkmeasurement.core.config.data.repository

import com.daisy.networkmeasurement.core.config.domain.model.TestMode

fun String?.toTestMode(): TestMode =
    when (this) {
        "speed_test" -> TestMode.SPEED_TEST
        "ping_test" -> TestMode.PING_TEST
        else -> TestMode.DEFAULT
    }

fun TestMode.toStorageValue(): String =
    when (this) {
        TestMode.SPEED_TEST -> "speed_test"
        TestMode.PING_TEST -> "ping_test"
    }