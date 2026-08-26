package com.daisy.networkmeasurement.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [
        SpeedTestEntity::class,
    ],
    version = 1,
)
@TypeConverters(
    InstantConverter::class,
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getSpeedTestDao(): SpeedTestDao
}