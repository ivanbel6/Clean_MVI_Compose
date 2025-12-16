package com.example.domain.usecase.networkUseCases

import com.example.domain.errorHandling.AppResult
import com.example.domain.repository.NetworkRepository
import com.example.domain.usecase.networkUseCases.networkError.NetworkError
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveInternetConnection @Inject constructor(
    private val repository: NetworkRepository,
) {
    //operator fun invoke(): Flow<Boolean> = repo.observeNetwork()
    operator fun invoke(): Flow<AppResult<Boolean, NetworkError>> =
        repository.observeNetwork()
}
