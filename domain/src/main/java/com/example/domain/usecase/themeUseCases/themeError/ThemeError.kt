package com.example.domain.usecase.themeUseCases.themeError

import com.example.domain.errorHandling.Error

sealed interface ThemeError : Error {
    data object Storage : ThemeError
    data object Unknown : ThemeError
}