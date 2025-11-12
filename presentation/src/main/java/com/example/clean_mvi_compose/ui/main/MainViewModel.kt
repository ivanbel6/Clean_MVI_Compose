package com.example.clean_mvi_compose.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.networkUseCases.ObserveInternetConnection
import com.example.domain.usecase.themeUseCases.GetTheme
import com.example.domain.usecase.themeUseCases.SetTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setThemeUseCase: SetTheme,
    private val getThemeUseCase: GetTheme,
    private val checkNetwork: ObserveInternetConnection,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        observeTheme()
        observeNetwork()
    }
    fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.LoadTheme -> observeTheme()
            is MainIntent.ToggleTheme -> toggleTheme(intent.isDark)
            is MainIntent.CheckInternet -> checkNetwork()
        }
    }

    private fun observeTheme() {
        viewModelScope.launch {
            getThemeUseCase()
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
            runCatching { setThemeUseCase(isDark) }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(error = e.message ?: "Не удалось изменить тему")
                    }
                }
        }
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            checkNetwork()
                .onEach { isConnected ->
                    _uiState.update { it.copy(netWork = isConnected) }
                }
                .catch { e ->
                    _uiState.update { it.copy(error = e.message ?: "Ошибка сети") }
                }
                .launchIn(this)
        }
    }



}
