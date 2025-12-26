package com.example.clean_mvi_compose.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object HomeScreen : Route
    @Serializable
    data object SettingsPage : Route
}