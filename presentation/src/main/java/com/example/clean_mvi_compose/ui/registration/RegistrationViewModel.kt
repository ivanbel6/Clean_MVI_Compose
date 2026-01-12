package com.example.clean_mvi_compose.ui.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegistrationViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(RegistrationState())
    val state: StateFlow<RegistrationState> = _state

    fun onIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.EmailChanged -> {
                updateState(email = intent.value)
            }

            is RegistrationIntent.PasswordChanged -> {
                updateState(password = intent.value)
            }

            RegistrationIntent.Submit -> {
                register()
            }

            RegistrationIntent.AppleSignIn -> Log.v("IntentTest", "AppleSignIn")
            RegistrationIntent.GitHubSignIn -> Log.v("IntentTest", "GitHubSignIn")
            RegistrationIntent.GoogleSignIn -> Log.v("IntentTest", "GoogleSignIn")
            RegistrationIntent.NavigateToLogin -> Log.v("IntentTest", "NavigateToLogin")
        }
    }

    private fun updateState(
        email: String = _state.value.email,
        password: String = _state.value.password,
    ) {
        val emailError = if (email.isNotBlank() && !isValidEmail(email)) {
            "Некорректный email"
        } else null

        val passwordError = if (password.isNotBlank() && password.length < 6) {
            "Минимум 6 символов"
        } else null

        _state.value = _state.value.copy(
            email = email,
            password = password,
            emailError = emailError,
            passwordError = passwordError,
            isSubmitEnabled = emailError == null && passwordError == null && email.isNotBlank() && password.isNotBlank()
        )
    }

    private fun register() {

    }

    private fun isValidEmail(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
