package com.example.domain.usecase.themeUseCases

import com.example.domain.domainErrors.Result
import com.example.domain.domainErrors.ThemeError
import com.example.domain.repository.ThemeRepository
import javax.inject.Inject

class SetTheme @Inject constructor(private val repository: ThemeRepository) {

    suspend operator fun invoke(isDark: Boolean): Result<Unit, ThemeError> =
        repository.setTheme(isDark)
}
