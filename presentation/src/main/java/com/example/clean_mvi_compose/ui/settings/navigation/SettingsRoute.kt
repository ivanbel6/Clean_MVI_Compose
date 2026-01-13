package com.example.clean_mvi_compose.ui.settings.navigation

import androidx.compose.runtime.Composable
import com.example.clean_mvi_compose.ui.settings.SettingsScreen

@Composable
fun SettingsRoute(
    onBack: () -> Unit
) {
    SettingsScreen(
        onBack = onBack
    )
}
