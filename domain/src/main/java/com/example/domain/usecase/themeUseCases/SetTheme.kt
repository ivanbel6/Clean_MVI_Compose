package com.example.domain.usecase.themeUseCases

import com.example.domain.errorHandling.AppResult
import com.example.domain.repository.ThemeRepository
import com.example.domain.usecase.themeUseCases.themeError.ThemeError
import javax.inject.Inject

class SetTheme @Inject constructor(private val repository: ThemeRepository) {

    //suspend operator fun invoke(isItDarkTheme: Boolean) = themeRepository.setTheme(isItDarkTheme)
    suspend operator fun invoke(isDark: Boolean): AppResult<Unit, ThemeError> =
        repository.setTheme(isDark)
}
