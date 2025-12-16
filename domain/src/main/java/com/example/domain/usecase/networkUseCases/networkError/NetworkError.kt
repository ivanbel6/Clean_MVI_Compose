package com.example.domain.usecase.networkUseCases.networkError

import com.example.domain.errorHandling.Error

sealed interface NetworkError : Error {
    data object NoConnection : NetworkError
    data object Unknown : NetworkError
}