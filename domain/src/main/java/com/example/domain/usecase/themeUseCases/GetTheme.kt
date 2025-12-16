package com.example.domain.usecase.themeUseCases

import com.example.domain.errorHandling.AppResult
import com.example.domain.repository.ThemeRepository
import com.example.domain.usecase.themeUseCases.themeError.ThemeError
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetTheme @Inject constructor(
    private val repository: ThemeRepository
) {
    //operator fun invoke(): Flow<Boolean> = repository.getTheme()
    operator fun invoke(): Flow<AppResult<Boolean, ThemeError>> = repository.getTheme()
}