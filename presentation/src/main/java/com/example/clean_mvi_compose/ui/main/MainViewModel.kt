package com.example.clean_mvi_compose.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.themeUseCases.GetTheme
import com.example.domain.usecase.themeUseCases.SetTheme
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val setThemeUseCase: SetTheme,
    private val getThemeUseCase: GetTheme
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.LoadTheme -> observeTheme()
            is MainIntent.ToggleTheme -> toggleTheme(intent.isDark)
        }
    }

    private fun observeTheme() {
        viewModelScope.launch {
            getThemeUseCase.getTheme()
                .distinctUntilChanged()
                .onStart { _uiState.update { it.copy(isLoading = true) } }
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = e.message ?: "Ошибка при загрузке темы"
                        )
                    }
                }
                .collect { isDark ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isDarkTheme = isDark,
                            error = null
                        )
                    }
                }
        }
    }

    private fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            runCatching { setThemeUseCase.set(isDark) }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(error = e.message ?: "Не удалось изменить тему")
                    }
                }
        }
    }
}
