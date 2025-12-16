package com.example.domain.errorHandling

typealias RootError = Error

sealed interface AppResult<out D, out E : RootError> {

    data class Success<out D>(
        val data: D
    ) : AppResult<D, Nothing>

    data class Failure<out E : RootError>(
        val error: E
    ) : AppResult<Nothing, E>
}