package com.example.clean_mvi_compose.ui.main.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object HomeScreen : Route

    @Serializable
    data object SettingsPage : Route

    @Serializable
    data object RegistrationPage : Route

    @Serializable
    data object Onboarding : Route


}