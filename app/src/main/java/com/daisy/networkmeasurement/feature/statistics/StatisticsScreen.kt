@file:OptIn(ExperimentalMaterial3Api::class)

package com.daisy.networkmeasurement.feature.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daisy.networkmeasurement.R
import com.daisy.networkmeasurement.feature.statistics.components.DateHeader
import com.daisy.networkmeasurement.feature.statistics.components.SpeedMeasurementRow
import com.daisy.networkmeasurement.feature.test.speedtest.domain.model.SpeedTestResult
import com.daisy.networkmeasurement.ui.theme.NetworkMeasurementTheme
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days

@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: StatisticsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    StatisticsScreenContent(
        state = state,
        modifier = modifier
    )
}

@Composable
private fun StatisticsScreenContent(
    state: StatisticsUiState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        when {
            state.isLoading -> {
                StatisticsLoading(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            state.measurements.isEmpty() -> {
                EmptyStatistics(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            else -> {
                StatisticsContent(
                    measurements = state.measurements,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun StatisticsContent(
    measurements: List<SpeedTestResult>,
    modifier: Modifier = Modifier,
) {
    val timeZone = TimeZone.currentSystemDefault()

    val groupedMeasurements = remember(measurements) {
        measurements.groupBy { measurement ->
            measurement.measuredAt
                .toLocalDateTime(timeZone)
                .date
        }
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 16.dp,
        ),
    ) {
        groupedMeasurements.forEach { (date, measurementsForDate) ->
            item(key = "header-$date") {
                DateHeader(date)
            }

            items(
                items = measurementsForDate,
                key = { it.id },
            ) { measurement ->
                SpeedMeasurementRow(measurement)
                HorizontalDivider()
            }

            item(key = "spacer-$date") {
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }
        }
    }
}

@Composable
private fun EmptyStatistics(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.statistics_empty_title),
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.statistics_empty_subtitle),
            modifier = Modifier.widthIn(max = 320.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun StatisticsLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview(showBackground = true)
private fun StatisticsPreview() {
    val now = Clock.System.now()

    NetworkMeasurementTheme {
        StatisticsScreenContent(
            state = StatisticsUiState(
                measurements = listOf(
                    SpeedTestResult(
                        id = 0,
                        averageSpeedMbps = 50.5,
                        peakSpeedMbps = 78.9,
                        measuredAt = now
                    ),
                    SpeedTestResult(
                        id = 1,
                        averageSpeedMbps = 150.5,
                        peakSpeedMbps = 788.9,
                        measuredAt = now - 1.days,
                    ),
                    SpeedTestResult(
                        id = 2,
                        averageSpeedMbps = 20.2,
                        peakSpeedMbps = 788.9,
                        measuredAt = now,
                    ),
                    SpeedTestResult(
                        id = 3,
                        averageSpeedMbps = 80.5,
                        peakSpeedMbps = 100.0,
                        measuredAt = now - 24.days,
                    ),
                ),
                isLoading = false
            )
        )
    }
}