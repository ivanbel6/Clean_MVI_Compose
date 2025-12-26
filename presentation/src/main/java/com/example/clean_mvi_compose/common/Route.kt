package com.example.clean_mvi_compose.common

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object HomeScreen : Route
    @Serializable
    data object SecondPage : Route
}