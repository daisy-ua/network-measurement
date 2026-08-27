@file:OptIn(ExperimentalMaterial3Api::class)

package com.daisy.networkmeasurement.feature.statistics.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.daisy.networkmeasurement.R


@Composable
fun StatisticsTopBar() {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = stringResource(R.string.statistics_title),
                    style = MaterialTheme.typography.titleLarge,
                )

                Text(
                    text = stringResource(R.string.statistics_subtitle),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
    )
}