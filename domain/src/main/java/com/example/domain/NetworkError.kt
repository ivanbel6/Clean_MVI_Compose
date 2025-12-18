package com.example.domain

sealed interface NetworkError : Error {
    enum class Network : Error {
        NO_INTERNET,
        UNKNOWN_ERROR,
    }

    enum class Local : Error {
        DISK_FULL
    }
}