package com.daisy.networkmeasurement.feature.test.speedtest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
}

@Composable
private fun SpeedTestContent(
    state: SpeedTestUiState,
    onStart: () -> Unit,
    onStop: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))

        SpeedValue(
            speedMbps = when (state) {
                SpeedTestUiState.Idle -> null
                is SpeedTestUiState.Running -> state.currentMbps
                is SpeedTestUiState.Completed -> null
                is SpeedTestUiState.Error -> null
            },
        )

        Spacer(Modifier.height(32.dp))

        when (state) {
            SpeedTestUiState.Idle -> Unit

            is SpeedTestUiState.Running -> Unit

            is SpeedTestUiState.Completed -> {
                SpeedStats(
                    averageMbps = state.averageMbps,
                    peakMbps = state.peakMbps,
                )
            }

            is SpeedTestUiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }

        Spacer(Modifier.weight(1f))

        SpeedTestButton(
            isRunning = state is SpeedTestUiState.Running,
            onStart = onStart,
            onStop = onStop,
        ) {
            Text(
                text = when (state) {
                    is SpeedTestUiState.Completed,
                    is SpeedTestUiState.Error -> "Start Again"

                    is SpeedTestUiState.Running -> "Stop"
                    is SpeedTestUiState.Idle -> "Start"
                },
            )
        }
    }
}

@Composable
private fun SpeedValue(
    speedMbps: Double?,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = speedMbps
                ?.let { "%.1f".format(it) }
                ?: "—",
            style = MaterialTheme.typography.displayLarge,
        )

        Text(
            text = "Mbps",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun SpeedStats(
    averageMbps: Double,
    peakMbps: Double,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(48.dp),
    ) {
        SpeedStat(
            label = "Average",
            value = averageMbps,
        )

        SpeedStat(
            label = "Peak",
            value = peakMbps,
        )
    }
}

@Composable
private fun SpeedStat(
    label: String,
    value: Double,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Text(
            text = "%.1f Mbps".format(value),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
private fun SpeedTestButton(
    isRunning: Boolean,
    onStart: () -> Unit,
    onStop: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit)
) {
    Button(
        modifier = modifier,
        onClick = {
            if (isRunning) {
                onStop()
            } else {
                onStart()
            }
        },
        content = content
    )
}

@Composable
@Preview(showBackground = true)
private fun SpeedTestContentPreview() {
    NetworkMeasurementTheme {
        SpeedTestContent(
//            state = SpeedTestUiState.Error("pam pam pam"),
//            state = SpeedTestUiState.Completed(50.5, 55.5),
            state = SpeedTestUiState.Running(1, 2.0, 3.0),
            onStart = {},
            onStop = {}
        )
    }
}