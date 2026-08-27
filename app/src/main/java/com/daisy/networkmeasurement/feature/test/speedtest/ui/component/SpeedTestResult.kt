package com.daisy.networkmeasurement.feature.test.speedtest.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.daisy.networkmeasurement.R
import com.daisy.networkmeasurement.feature.test.speedtest.ui.SpeedTestUiState


@Composable
fun SpeedTestResult(
    state: SpeedTestUiState,
) {
    when (state) {
        SpeedTestUiState.Idle -> {
            Text(
                text = stringResource(R.string.speed_test_idle_message),
                style = MaterialTheme.typography.titleMedium,
            )
        }

        is SpeedTestUiState.Connecting -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()

                Spacer(Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.speed_test_connecting_title),
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.speed_test_connecting_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        is SpeedTestUiState.Running -> {
            SpeedValue(
                speedMbps = state.currentMbps,
            )
        }

        is SpeedTestUiState.Completed -> {
            SpeedStats(
                averageMbps = state.averageMbps,
                peakMbps = state.peakMbps,
            )
        }

        is SpeedTestUiState.Error -> {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.speed_test_failed),
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = state.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}