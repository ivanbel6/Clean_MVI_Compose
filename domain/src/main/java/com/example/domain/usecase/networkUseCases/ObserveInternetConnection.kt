package com.example.domain.usecase.networkUseCases

import com.example.domain.NetworkError
import com.example.domain.Result
import com.example.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveInternetConnection @Inject constructor(
    private val repo: NetworkRepository,
) {
    operator fun invoke(): Flow<Result<Boolean, NetworkError.Network>> = repo.observeNetwork()
}
