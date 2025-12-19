package com.example.domain

sealed interface ThemeError : Error {
    enum class Errors : ThemeError{
        READ_ERROR,

        WRITE_ERROR,

        UNKNOWN
    }

}