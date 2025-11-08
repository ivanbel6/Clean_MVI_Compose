package com.example.clean_mvi_compose.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.ThemeRepositoryImpl
import com.example.domain.usecase.themeUseCases.GetTheme
import com.example.domain.usecase.themeUseCases.SetTheme

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val repository = ThemeRepositoryImpl(context)
    private val getTheme = GetTheme(repository)
    private val setTheme = SetTheme(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(setTheme, getTheme) as T
    }
}
