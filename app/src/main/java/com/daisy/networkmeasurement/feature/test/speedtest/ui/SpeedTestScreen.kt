package com.daisy.networkmeasurement.feature.test.speedtest.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daisy.networkmeasurement.feature.test.speedtest.ui.component.SpeedTestButton
import com.daisy.networkmeasurement.feature.test.speedtest.ui.component.SpeedTestResult
import com.daisy.networkmeasurement.ui.theme.NetworkMeasurementTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SpeedTestScreen(
    viewModel: SpeedTestViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SpeedTestContent(
        state = state,
        onStart = viewModel::start,
        onStop = viewModel::stop
    )

    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        viewModel.stop()
    }
}

@Composable
private fun SpeedTestContent(
    state: SpeedTestUiState,
    onStart: () -> Unit,
    onStop: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            SpeedTestResult(state)
        }

        SpeedTestButton(
            state = state,
            onStart = onStart,
            onStop = onStop,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SpeedTestContentPreview() {
    NetworkMeasurementTheme {
        SpeedTestContent(
//            state = SpeedTestUiState.Error("pam pam pam"),
            state = SpeedTestUiState.Completed(50.5, 55.5),
//            state = SpeedTestUiState.Running(1, 2.0, 3.0),
//            state = SpeedTestUiState.Connecting,
//            state = SpeedTestUiState.Idle,
            onStart = {},
            onStop = {}
        )
    }
}