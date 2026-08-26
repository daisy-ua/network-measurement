package com.daisy.networkmeasurement.core.database.di

import androidx.room.Room
import com.daisy.networkmeasurement.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        Room
            .databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                "network-measurement-database",
            ).build()
    }

    single { get<AppDatabase>().getSpeedTestDao() }
}