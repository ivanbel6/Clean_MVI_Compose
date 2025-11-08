package com.example.clean_mvi_compose.ui.main

data class MainUiState(
    val isDarkTheme: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
