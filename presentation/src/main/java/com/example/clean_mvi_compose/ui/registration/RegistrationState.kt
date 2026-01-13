package com.example.clean_mvi_compose.ui.registration

import com.example.domain.domainErrors.AccountError

data class RegistrationState(
    val email: String = "",
    val password: String = "",
    val isSubmitEnabled: Boolean = false,

    val regEmailError: AccountError.Register? = null,
    val regPasswordError: AccountError.Register? = null,
    val authEmailError: AccountError.Authorisation? = null,
    val authPasswordError: AccountError.Authorisation? = null,
)
