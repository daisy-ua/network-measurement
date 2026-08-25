package com.daisy.networkmeasurement.core.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.INFO
            }
        }
    }

    single {
        Json {
            ignoreUnknownKeys = true
        }
    }
}