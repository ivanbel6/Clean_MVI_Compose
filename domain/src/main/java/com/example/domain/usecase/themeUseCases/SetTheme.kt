package com.example.domain.usecase.themeUseCases

import com.example.domain.repository.ThemeRepository

class SetTheme(val themeRepository: ThemeRepository) {

    suspend fun set(isItDarkTheme: Boolean) = themeRepository.setTheme(isItDarkTheme)
}
