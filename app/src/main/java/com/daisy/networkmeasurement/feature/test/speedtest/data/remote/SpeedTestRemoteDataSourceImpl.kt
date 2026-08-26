package com.daisy.networkmeasurement.feature.test.speedtest.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeoutConfig.Companion.INFINITE_TIMEOUT_MS
import io.ktor.client.plugins.timeout
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.utils.io.CancellationException
import io.ktor.utils.io.cancel
import io.ktor.utils.io.readAvailable
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlin.math.max
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit
import kotlin.time.TimeSource

class SpeedTestRemoteDataSourceImpl(
    private val client: HttpClient,
) : SpeedTestRemoteDataSource {
    override fun measureDownloadSpeed(targetDuration: Duration): Flow<SpeedTestRemoteState> = flow {
        client.prepareGet(MEASUREMENT_URL) {
            parameter(PARAM_BYTES_KEY, PARAM_BYTES_VALUE)

            header(HttpHeaders.Referrer, MEASUREMENT_REFERER)

            timeout {
                requestTimeoutMillis = INFINITE_TIMEOUT_MS
                connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
                socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
            }
        }.execute { response ->
            if (!response.status.isSuccess()) {
                emit(
                    SpeedTestRemoteState.Error(
                        code = response.status.value,
                        message = response.status.description,
                    )
                )
                return@execute
            }

            val channel = response.bodyAsChannel()
            val startTime = TimeSource.Monotonic.markNow()

            var totalBytes = 0L
            var intervalBytes = 0L
            var lastUpdate = startTime
            var peakMbps = 0.0

            val buffer = ByteArray(64 * 1024)

            try {
                while (!channel.isClosedForRead &&
                    startTime.elapsedNow() < targetDuration &&
                    currentCoroutineContext().isActive
                ) {
                    val read = channel.readAvailable(buffer)
                    if (read == -1) break

                    totalBytes += read
                    intervalBytes += read

                    val intervalDuration = lastUpdate.elapsedNow()

                    if (intervalDuration >= INTERVAL_DURATION) {
                        val seconds = intervalDuration.toDouble(DurationUnit.SECONDS)
                        val currentMbps = (intervalBytes * 8) / (seconds * 1_000_000.0)

                        peakMbps = max(peakMbps, currentMbps)

                        emit(
                            SpeedTestRemoteState.Running(
                                currentMbps = currentMbps,
                                peakMbps = peakMbps,
                                durationElapsed = startTime.elapsedNow()
                            )
                        )

                        intervalBytes = 0L
                        lastUpdate = TimeSource.Monotonic.markNow()
                    }
                }

                val totalSeconds = startTime.elapsedNow().toDouble(DurationUnit.SECONDS)
                val averageMbps =
                    if (totalSeconds > 0) (totalBytes * 8) / (totalSeconds * 1_000_000.0) else 0.0

                emit(
                    SpeedTestRemoteState.Completed(
                        averageMbps = averageMbps,
                        peakMbps = peakMbps
                    )
                )
            } catch (_: HttpRequestTimeoutException) {
                emit(SpeedTestRemoteState.Error(code = null, message = "Timed out"))
            } catch (e: CancellationException) {
                throw e
            } finally {
                channel.cancel()
            }
        }
    }

    private companion object {
        const val MEASUREMENT_URL: String = "https://speed.cloudflare.com/__down"
        const val MEASUREMENT_REFERER: String = "https://speed.cloudflare.com/"
        const val PARAM_BYTES_KEY: String = "bytes"
        const val PARAM_BYTES_VALUE: Int = 200_000_000
        const val CONNECT_TIMEOUT_MILLIS: Long = 5_000
        const val SOCKET_TIMEOUT_MILLIS: Long = 5_000
        val INTERVAL_DURATION: Duration = 200.milliseconds
    }
}