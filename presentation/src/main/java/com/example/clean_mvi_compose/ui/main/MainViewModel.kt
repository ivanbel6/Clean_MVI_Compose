package com.example.clean_mvi_compose.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.errorHandling.AppResult
import com.example.domain.usecase.networkUseCases.ObserveInternetConnection
import com.example.domain.usecase.themeUseCases.GetTheme
import com.example.domain.usecase.themeUseCases.SetTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setThemeUseCase: SetTheme,
    private val getThemeUseCase: GetTheme,
    private val observeNetworkUseCase: ObserveInternetConnection
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
            is MainIntent.CheckInternet -> observeNetwork()
        }
    }

    private fun observeTheme() {
        getThemeUseCase()
            .onEach { result ->
                when (result) {
                    is AppResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isDarkTheme = result.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }

                    is AppResult.Failure -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.error.toUiText()
                            )
                        }
                    }
                }
            }
            .onStart {
                _uiState.update { it.copy(isLoading = true) }
            }
            .launchIn(viewModelScope)
    }

    private fun observeNetwork() {
        observeNetworkUseCase()
            .onEach { result ->
                when (result) {
                    is AppResult.Success ->
                        _uiState.update { it.copy(netWork = result.data) }

                    is AppResult.Failure ->
                        _uiState.update { it.copy(error = result.error.toUiText()) }
                }
            }
            .launchIn(viewModelScope)
    }

    fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            when (val result = setThemeUseCase(isDark)) {
                is AppResult.Success -> Unit
                is AppResult.Failure ->
                    _uiState.update { it.copy(error = result.error.toUiText()) }
            }
        }
    }
}
