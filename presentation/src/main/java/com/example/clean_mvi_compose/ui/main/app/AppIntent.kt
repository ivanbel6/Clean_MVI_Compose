package com.example.clean_mvi_compose.ui.main.app

sealed interface AppIntent {
    data class ToggleTheme(val isDark: Boolean) : AppIntent

}
