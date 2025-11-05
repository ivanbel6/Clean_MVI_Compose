package com.example.domain.usecase.themeUseCases

import com.example.domain.repository.ThemeRepository

class GetTheme(val themeRepository: ThemeRepository) {
    suspend fun getThem() = themeRepository.getTheme()
}