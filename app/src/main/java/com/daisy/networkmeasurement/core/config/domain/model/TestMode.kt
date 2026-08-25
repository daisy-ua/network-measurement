package com.daisy.networkmeasurement.core.config.domain.model

enum class TestMode {
    SPEED_TEST,

    PING_TEST;

    companion object {
        val DEFAULT = SPEED_TEST
    }
}
