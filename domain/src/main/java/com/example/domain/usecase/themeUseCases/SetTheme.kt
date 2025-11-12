package com.example.domain.usecase.themeUseCases

import com.example.domain.repository.ThemeRepository
import javax.inject.Inject

class SetTheme @Inject constructor(private val themeRepository: ThemeRepository) {

    suspend operator fun invoke(isItDarkTheme: Boolean) = themeRepository.setTheme(isItDarkTheme)
}
