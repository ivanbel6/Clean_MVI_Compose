package com.example.domain.repository

import kotlinx.coroutines.flow.Flow


interface ThemeRepository {
    fun getTheme(): Flow<Boolean>
    suspend fun setTheme(isDark: Boolean)
}