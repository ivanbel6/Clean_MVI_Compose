package com.example.clean_mvi_compose.ui.registration

data class RegistrationState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isSubmitEnabled: Boolean = false
)
