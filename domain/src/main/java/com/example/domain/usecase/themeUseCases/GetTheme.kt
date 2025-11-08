package com.example.domain.usecase.themeUseCases

import com.example.domain.repository.ThemeRepository

class GetTheme(val themeRepository: ThemeRepository) {
    fun getTheme() = themeRepository.getTheme()
}