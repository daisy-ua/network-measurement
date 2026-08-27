package com.daisy.networkmeasurement.feature.statistics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.daisy.networkmeasurement.R
import com.daisy.networkmeasurement.core.ui.util.formatSpeed
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestResult
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@Composable
fun SpeedMeasurementRow(
    measurement: SpeedTestResult,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SpeedMetric(
            label = stringResource(R.string.speed_average),
            speedMbps = measurement.averageSpeedMbps,
            modifier = Modifier.weight(1f),
        )

        VerticalDivider(
            modifier = Modifier
                .height(36.dp)
                .padding(horizontal = 16.dp),
        )

        SpeedMetric(
            label = stringResource(R.string.speed_peak),
            speedMbps = measurement.peakSpeedMbps,
            modifier = Modifier.weight(1f),
        )

        Text(
            text = formatTime(measurement.measuredAt),
            modifier = Modifier.padding(start = 16.dp).align(Alignment.Bottom),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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

fun formatTime(timestamp: Instant): String =
    timestamp
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .time
        .toString()
        .substringBeforeLast(':')