package com.example.clean_mvi_compose.di

import android.content.Context
import android.content.res.Resources
import com.example.data.repository.NetworkRepositoryImpl
import com.example.data.repository.ThemeRepositoryImpl
import com.example.domain.repository.NetworkRepository
import com.example.domain.repository.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideThemeRepository(
        @ApplicationContext context: Context,
    ): ThemeRepository = ThemeRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideNetworkRepository(
        @ApplicationContext context: Context,
    ): NetworkRepository = NetworkRepositoryImpl(context)



}