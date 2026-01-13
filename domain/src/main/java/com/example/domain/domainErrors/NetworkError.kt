package com.example.domain.domainErrors

sealed interface NetworkError : Error {
    enum class Network : NetworkError {
        NO_INTERNET,
        UNKNOWN_ERROR,
    }

}