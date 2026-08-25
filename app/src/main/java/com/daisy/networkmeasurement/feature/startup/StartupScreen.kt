package com.daisy.networkmeasurement.feature.startup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daisy.networkmeasurement.R
import com.daisy.networkmeasurement.core.config.domain.model.TestMode
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun StartupScreen(
    onTestModeLoaded: (TestMode) -> Unit,
    viewModel: StartupViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    StartupContent(
        state = state,
        onRetry = viewModel::loadTestMode,
    )

    LaunchedEffect(Unit) {
        viewModel.uiState
            .filterIsInstance<StartupState.Success>()
            .first()
            .also { success ->
                onTestModeLoaded(success.testMode)
            }
    }
}

@Composable
private fun StartupContent(
    state: StartupState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (state) {
            is StartupState.Loading -> {
                CircularProgressIndicator()
            }

            is StartupState.Error -> {
                Button(
                    onClick = onRetry
                ) {
                    Text(
                        text = stringResource(R.string.startup_label_retry)
                    )
                }
            }

            else -> {}
        }
    }
}