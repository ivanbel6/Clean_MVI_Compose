package com.example.domain.repository

import com.example.domain.Result
import com.example.domain.ThemeError
import kotlinx.coroutines.flow.Flow


interface ThemeRepository {

    fun observeTheme(): Flow<Result<Boolean, ThemeError>>
    suspend fun setTheme(isDark: Boolean): Result<Unit, ThemeError>

}