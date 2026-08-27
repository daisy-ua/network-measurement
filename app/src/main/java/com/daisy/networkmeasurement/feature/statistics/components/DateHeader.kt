package com.daisy.networkmeasurement.feature.statistics.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

private fun formatDateHeader(
    date: LocalDate,
): String {
    val today = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    return when (date) {
        today -> "TODAY"
        today.minus(DatePeriod(days = 1)) -> "YESTERDAY"
        else -> "${monthNames[date.month.ordinal]} ${date.day}, ${date.year}"
    }
}

private val monthNames = arrayOf(
    "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
    "JUL", "AUG", "SEP", "OCT", "NOV", "DEC",
)
