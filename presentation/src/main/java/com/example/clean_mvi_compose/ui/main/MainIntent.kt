package com.example.clean_mvi_compose.ui.main

sealed interface MainIntent {
    data object LoadTheme : MainIntent
    data class ToggleTheme(val isDark: Boolean) : MainIntent
}
