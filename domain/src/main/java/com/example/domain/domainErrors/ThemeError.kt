package com.example.domain.domainErrors

sealed interface ThemeError : Error {
    enum class Errors : ThemeError {
        GET_THEME_ERROR,
        SET_THEME_ERROR,
    }

}