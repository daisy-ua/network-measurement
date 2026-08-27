package com.daisy.networkmeasurement.feature.statistics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestResult


@Composable
fun SpeedMeasurementRow(
    measurement: SpeedTestResult,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SpeedMetric(
            label = "Average",
            speedMbps = measurement.averageSpeedMbps,
        )

        SpeedMetric(
            label = "Peak",
            speedMbps = measurement.peakSpeedMbps,
        )
    }
}

@Composable
private fun SpeedMetric(
    label: String,
    speedMbps: Double,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Text(
            text = formatSpeed(speedMbps),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )
    }
}


private fun formatSpeed(
    mbps: Double,
): String = when {
    mbps >= 100 -> "%.0f Mbps".format(mbps)
    else -> "%.1f Mbps".format(mbps)
}
