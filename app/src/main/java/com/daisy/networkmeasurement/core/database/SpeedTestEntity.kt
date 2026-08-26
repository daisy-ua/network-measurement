package com.daisy.networkmeasurement.core.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Instant

@Entity(tableName = "speed_test_measurements")
data class SpeedTestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "average_mbps")
    val averageMbps: Double,

    @ColumnInfo(name = "peak_mbps")
    val peakMbps: Double,

    val timestamp: Instant,
)