package com.example.data.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.domain.domainErrors.Result
import com.example.domain.domainErrors.Result.Success
import com.example.domain.domainErrors.ThemeError
import com.example.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


val Context.dataStore by preferencesDataStore("settings")


class ThemeRepositoryImpl @Inject constructor(val context: Context) : ThemeRepository {
    companion object {
        var IS_DARK_THEME_KEY = booleanPreferencesKey("them_key")
    }

    override fun observeTheme(): Flow<Result<Boolean, ThemeError>> =
        context.dataStore.data
            .map<Preferences, Result<Boolean, ThemeError>> { preferences ->
                Success(
                    preferences[IS_DARK_THEME_KEY] ?: false
                )
            }
            .catch {
                emit(Result.Error(ThemeError.Errors.GET_THEME_ERROR))
            }

    override suspend fun setTheme(isDark: Boolean): Result<Unit, ThemeError> =
        try {
            context.dataStore.edit {
                it[IS_DARK_THEME_KEY] = isDark
            }
            Success(Unit)
        } catch (e: Exception) {
            Result.Error(ThemeError.Errors.SET_THEME_ERROR)
        }
}

