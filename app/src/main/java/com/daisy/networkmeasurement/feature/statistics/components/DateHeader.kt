package com.daisy.networkmeasurement.feature.statistics.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.daisy.networkmeasurement.R
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock


@Composable
fun DateHeader(
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    Text(
        text = formatDateHeader(date),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                bottom = 8.dp,
            ),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

@Composable
private fun formatDateHeader(
    date: LocalDate
): String {
    val today = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    return when (date) {
        today -> stringResource(R.string.statistics_date_today)
        today.minus(DatePeriod(days = 1)) -> stringResource(R.string.statistics_date_yesterday)
        else -> {
            val months = stringArrayResource(R.array.month_short_names)
            stringResource(
                R.string.statistics_date_fallback,
                months[date.month.ordinal],
                date.day,
                date.year
            )
        }
    }
}

