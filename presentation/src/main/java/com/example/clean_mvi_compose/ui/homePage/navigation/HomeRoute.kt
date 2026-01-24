package com.example.clean_mvi_compose.ui.homePage.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.clean_mvi_compose.ui.homePage.HomeScreen
import com.example.clean_mvi_compose.ui.main.AppViewModel

@Composable
fun HomeRoute(
    onNavigateToSettings: () -> Unit,
    onNavigateToRegistration: () -> Unit,
    onNavigateToOnBoarding: () -> Unit
) {
    val appViewModel: AppViewModel = hiltViewModel()
    val appUiState by appViewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = appUiState,
        onItemClick = {},
        onNavigateToSettings = onNavigateToSettings,
        onNavigateToRegistration = onNavigateToRegistration,
        onNavigateToOnBoarding = onNavigateToOnBoarding,
        vmIntent = appViewModel::handleIntent
    )
}
