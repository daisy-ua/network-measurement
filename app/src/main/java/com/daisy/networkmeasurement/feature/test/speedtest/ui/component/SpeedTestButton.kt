package com.daisy.networkmeasurement.feature.test.speedtest.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.daisy.networkmeasurement.R
import com.daisy.networkmeasurement.feature.test.speedtest.ui.SpeedTestUiState


@Composable
fun SpeedTestButton(
    state: SpeedTestUiState,
    onStart: () -> Unit,
    onStop: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val canStop = state is SpeedTestUiState.Connecting ||
            state is SpeedTestUiState.Running

    Button(
        modifier = modifier.height(56.dp),
        onClick = {
            if (canStop) {
                onStop()
            } else {
                onStart()
            }
        },
        colors = if (canStop) {
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError,
            )
        } else {
            ButtonDefaults.buttonColors()
        },
    ) {
        Text(
            text = when (state) {
                SpeedTestUiState.Idle -> stringResource(R.string.speed_test_label_start)

                is SpeedTestUiState.Connecting,
                is SpeedTestUiState.Running -> stringResource(R.string.speed_test_stop)

                is SpeedTestUiState.Completed,
                is SpeedTestUiState.Error -> stringResource(R.string.speed_test_start_again)
            },
        )
    }
}