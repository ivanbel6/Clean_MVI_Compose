package com.example.domain.repository

import com.example.domain.NetworkError
import com.example.domain.Result
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    fun observeNetwork(): Flow<Result<Boolean, NetworkError.Network>>

}