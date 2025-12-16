package com.example.clean_mvi_compose.ui.main

import com.example.domain.usecase.networkUseCases.networkError.NetworkError
import com.example.domain.usecase.themeUseCases.themeError.ThemeError

fun ThemeError.toUiText(): String =
    when (this) {
        ThemeError.Storage -> "Ошибка сохранения темы"
        ThemeError.Unknown -> "Неизвестная ошибка"
    }

fun NetworkError.toUiText(): String =
    when (this) {
        NetworkError.NoConnection -> "Нет подключения к интернету"
        NetworkError.Unknown -> "Ошибка сети"
    }