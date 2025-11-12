package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    //fun getNetwork() : Boolean
    fun observeNetwork(): Flow<Boolean>

}