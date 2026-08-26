package com.daisy.networkmeasurement.feature.test

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daisy.networkmeasurement.AppState
import com.daisy.networkmeasurement.core.config.domain.model.TestMode
import com.daisy.networkmeasurement.feature.test.speedtest.ui.SpeedTestScreen
import org.koin.compose.koinInject

@Composable
fun TestScreen(
    appState: AppState = koinInject(),
) {
    val testMode by appState.testMode.collectAsStateWithLifecycle()

    when (testMode) {
        TestMode.PING_TEST -> PingTestScreen()
        TestMode.SPEED_TEST -> SpeedTestScreen()
        else -> {
            Log.d("daisy-ua", "Something went wrong")
            SpeedTestScreen()
        }
    }
}