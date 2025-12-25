package com.example.clean_mvi_compose.ui.main

import com.example.domain.domainErrors.Error


data class MainUiState(
    val isDarkTheme: Boolean = false,
    val isLoading: Boolean = false,
    val netWork: Boolean = false,
    val error: Error? = null
)
