package com.example.domain.repository

import com.example.domain.domainErrors.NetworkError
import com.example.domain.domainErrors.Result
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    fun observeNetwork(): Flow<Result<Boolean, NetworkError>>

}