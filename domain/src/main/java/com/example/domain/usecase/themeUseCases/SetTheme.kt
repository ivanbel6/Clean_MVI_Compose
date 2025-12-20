package com.example.domain.usecase.themeUseCases

import com.example.domain.Result
import com.example.domain.ThemeError
import com.example.domain.repository.ThemeRepository
import javax.inject.Inject

class SetTheme @Inject constructor(private val repository: ThemeRepository) {

    //suspend operator fun invoke(isItDarkTheme: Boolean) = themeRepository.setTheme(isItDarkTheme)
    suspend operator fun invoke(isDark: Boolean): Result<Unit, ThemeError> =
        repository.setTheme(isDark)
}
