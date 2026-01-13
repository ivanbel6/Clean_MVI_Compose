package com.example.clean_mvi_compose.ui.registration.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.clean_mvi_compose.ui.registration.RegistrationScreen
import com.example.clean_mvi_compose.ui.registration.RegistrationViewModel

@Composable
fun RegistrationRoute(
    onBack: () -> Unit
) {
    val registrationViewModel : RegistrationViewModel = hiltViewModel()
    val registrationUiState by registrationViewModel.state.collectAsStateWithLifecycle()

    RegistrationScreen(
        state = registrationUiState,
        onIntent = registrationViewModel::onIntent,
        onBackClick = onBack
    )
}
