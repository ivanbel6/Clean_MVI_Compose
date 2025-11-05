package com.example.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore("settings")


class ThemeRepositoryImpl(val context: Context) : ThemeRepository {
    companion object {
        var IS_DARK_THEME_KEY = booleanPreferencesKey("them_key")
    }

    override suspend fun getTheme(): Flow<Boolean> =
        context.dataStore.data.map { preferences -> preferences[IS_DARK_THEME_KEY] ?: false }

    override suspend fun setTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_THEME_KEY] = isDark
        }
    }
}

