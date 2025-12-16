package com.example.domain.repository

import com.example.domain.errorHandling.AppResult
import com.example.domain.usecase.networkUseCases.networkError.NetworkError
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    //fun getNetwork() : Boolean
    fun observeNetwork(): Flow<AppResult<Boolean, NetworkError>>

}