package com.daisy.networkmeasurement.core.config.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.daisy.networkmeasurement.core.config.domain.model.TestMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TestModeLocalDataSource(
    private val dataStore: DataStore<Preferences>,
) {
    fun getTestMode(): Flow<TestMode?> =
        dataStore.data.map { preferences ->
            preferences[TEST_MODE_KEY]?.toTestMode()
        }

    suspend fun saveTestMode(mode: TestMode) {
        dataStore.edit { preferences ->
            preferences[TEST_MODE_KEY] = mode.toStorageValue()
        }
    }

    private companion object {
        val TEST_MODE_KEY = stringPreferencesKey("test_mode")
    }
}