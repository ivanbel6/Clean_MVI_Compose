package com.example.clean_mvi_compose.ui.registration

sealed interface RegistrationIntent {
    data class EmailChanged(val value: String) : RegistrationIntent
    data class PasswordChanged(val value: String) : RegistrationIntent
    data object Submit : RegistrationIntent

    data object NavigateToLogin : RegistrationIntent
    object GoogleSignIn : RegistrationIntent

}