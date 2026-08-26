package com.daisy.networkmeasurement.core.database

import androidx.room.TypeConverter
import kotlin.time.Instant

class InstantConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? = value?.let(Instant::fromEpochMilliseconds)

    @TypeConverter
    fun dateToTimestamp(instant: Instant?): Long? = instant?.toEpochMilliseconds()
}