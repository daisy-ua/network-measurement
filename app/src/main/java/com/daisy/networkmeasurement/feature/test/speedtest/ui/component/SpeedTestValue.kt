package com.daisy.networkmeasurement.feature.test.speedtest.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.daisy.networkmeasurement.R
import com.daisy.networkmeasurement.core.ui.util.formatSpeedValue


@Composable
fun SpeedValue(
    speedMbps: Double,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = formatSpeedValue(speedMbps),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            text = stringResource(R.string.speed_unit_mbps),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}