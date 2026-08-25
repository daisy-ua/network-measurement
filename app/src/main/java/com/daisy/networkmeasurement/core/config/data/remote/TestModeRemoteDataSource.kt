package com.daisy.networkmeasurement.core.config.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class TestModeRemoteDataSource(
    private val client: HttpClient,
    private val json: Json,
) {
    suspend fun getRemoteConfig(): TestModeDto? {
        val response = client.get(REMOTE_CONFIG_URL)

        return try {
            json.decodeFromString(response.bodyAsText())
        } catch (_: SerializationException) {
            null
        }
    }

    private companion object {
        const val REMOTE_CONFIG_URL =
            "https://raw.githubusercontent.com/daisy-ua/network-measurement/main/remote-config/config.json"
    }
}