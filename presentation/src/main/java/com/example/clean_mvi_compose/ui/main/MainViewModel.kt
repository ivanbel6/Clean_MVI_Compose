package com.example.clean_mvi_compose.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domainErrors.NetworkError
import com.example.domain.domainErrors.Result
import com.example.domain.domainErrors.ThemeError
import com.example.domain.usecase.networkUseCases.ObserveInternetConnection
import com.example.domain.usecase.themeUseCases.ObserveTheme
import com.example.domain.usecase.themeUseCases.SetTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
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
            observeThemeUseCase().distinctUntilChanged()
                .onStart { _uiState.update { it.copy(isLoading = true) } }.collect { isDark ->
                    when (isDark) {
                        is Result.Error -> _uiState.update {
                            it.copy(
                                error = isDark.error,
                                isLoading = false,
                            )
                        }

                        is Result.Success -> _uiState.update {
                            it.copy(
                                isLoading = false, isDarkTheme = isDark.data, error = null
                            )
                        }
                    }
                }
        }
    }


    private fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            when (val themeError = setThemeUseCase(isDark)) {
                is Result.Success -> Unit
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = themeError.error)
                    }
                }
            }
        }

    }

    private fun observeNetwork() {
        viewModelScope.launch {
            checkNetwork().collect { isConnected ->
                when (isConnected) {
                    is Result.Error -> _uiState.update { it.copy(error = isConnected.error) }
                    is Result.Success -> _uiState.update { it.copy(netWork = isConnected.data) }
                }
            }

        }
    }


}
