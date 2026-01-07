package com.example.domain.usecase.themeUseCases

import com.example.domain.domainErrors.Result
import com.example.domain.domainErrors.ThemeError
import com.example.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class ObserveTheme @Inject constructor(
    private val repository: ThemeRepository
) {
    operator fun invoke(): Flow<Result<Boolean, ThemeError>> = repository.observeTheme()
}