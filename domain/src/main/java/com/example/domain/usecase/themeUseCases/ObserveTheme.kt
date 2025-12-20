package com.example.domain.usecase.themeUseCases

import com.example.domain.Result
import com.example.domain.ThemeError
import com.example.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class ObserveTheme @Inject constructor(
    private val repository: ThemeRepository
) {
    //operator fun invoke(): Flow<Boolean> = repository.getTheme()
    operator fun invoke(): Flow<Result<Boolean, ThemeError>> = repository.observeTheme()
}