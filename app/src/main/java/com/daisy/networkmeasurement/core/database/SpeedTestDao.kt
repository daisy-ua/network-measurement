package com.daisy.networkmeasurement.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeedTestDao {
    @Query("SELECT * FROM speed_test_measurements ORDER BY timestamp DESC")
    fun getAllResults(): Flow<List<SpeedTestEntity>>

    @Insert
    suspend fun insert(result: SpeedTestEntity)
}