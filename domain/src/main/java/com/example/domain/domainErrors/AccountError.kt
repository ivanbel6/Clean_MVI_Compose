package com.example.domain.domainErrors

interface AccountError : Error {

    enum class Register : NetworkError {
        EMAIL_ERROR,
        PASSWORD_ERROR,
    }

    enum class Authorisation : NetworkError {
        EMAIL_ERROR,
        PASSWORD_ERROR,
    }
}