package com.example.domain.repository

import com.example.domain.errorHandling.AppResult
import com.example.domain.usecase.themeUseCases.themeError.ThemeError
import kotlinx.coroutines.flow.Flow


interface ThemeRepository {
    //fun getTheme(): Flow<Boolean>
    //suspend fun setTheme(isDark: Boolean)
    fun getTheme(): Flow<AppResult<Boolean, ThemeError>>
    suspend fun setTheme(isDark: Boolean): AppResult<Unit, ThemeError>

}