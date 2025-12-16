package com.example.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.domain.errorHandling.AppResult
import com.example.domain.usecase.themeUseCases.themeError.ThemeError
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

    override fun getTheme(): Flow<AppResult<Boolean, ThemeError>> =
        context.dataStore.data
            .map { preferences ->
                AppResult.Success(
                    preferences[IS_DARK_THEME_KEY] ?: false
                ) as AppResult<Boolean, ThemeError>
            }
            .catch {
                emit(AppResult.Failure(ThemeError.Storage))
            }

    override suspend fun setTheme(isDark: Boolean): AppResult<Unit, ThemeError> =
        try {
            context.dataStore.edit {
                it[IS_DARK_THEME_KEY] = isDark
            }
            AppResult.Success(Unit)
        } catch (e: Exception) {
            AppResult.Failure(ThemeError.Storage)
        }


//    override  fun getTheme(): Flow<Boolean> =
//        context.dataStore.data.map { preferences -> preferences[IS_DARK_THEME_KEY] ?: false }
//
//    override suspend fun setTheme(isDark: Boolean) {
//        context.dataStore.edit { preferences ->
//            preferences[IS_DARK_THEME_KEY] = isDark
//        }
//    }
}

