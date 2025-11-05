package com.example.clean_mvi_compose.ui.main

import android.content.Context
import com.example.data.repository.ThemeRepositoryImpl
import com.example.domain.usecase.themeUseCases.GetTheme
import com.example.domain.usecase.themeUseCases.SetTheme

class MainViewModel(
    setTheme : SetTheme ,
    getTheme : GetTheme,
    context: Context
) {
    private val repository = ThemeRepositoryImpl(context)
    private val getTheme = GetTheme(repository)
    private val setTheme = SetTheme(repository)

}
