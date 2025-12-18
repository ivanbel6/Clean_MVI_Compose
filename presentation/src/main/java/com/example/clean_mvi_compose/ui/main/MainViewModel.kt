package com.example.clean_mvi_compose.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.NetworkError
import com.example.domain.Result
import com.example.domain.usecase.networkUseCases.ObserveInternetConnection
import com.example.domain.usecase.themeUseCases.ObserveTheme
import com.example.domain.usecase.themeUseCases.SetTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setThemeUseCase: SetTheme,
    private val observeThemeUseCase: ObserveTheme,
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
            is MainIntent.ToggleTheme -> toggleTheme(intent.isDark)
        }
    }

    private fun observeTheme() {
        viewModelScope.launch {
            observeThemeUseCase()
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
            checkNetwork().onEach { isConnected ->
                when (isConnected) {
                    is Result.Error<*, *> ->
                        when (isConnected.error){
                            NetworkError.Local.DISK_FULL -> _uiState.update { it.copy(error = "DISK_FULL") }
                            NetworkError.Network.NO_INTERNET -> _uiState.update { it.copy(error = "NO_INTERNET") }
                            NetworkError.Network.UNKNOWN_ERROR -> _uiState.update { it.copy(error = "UNKNOWN_ERROR") }
                        }
                    is Result.Success<Boolean, *> -> _uiState.update { it.copy(netWork = isConnected.data) }
                }
            }.launchIn(viewModelScope)

        }
    }


}
