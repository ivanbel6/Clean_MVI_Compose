package com.example.domain.usecase.networkUseCases

import com.example.domain.domainErrors.NetworkError
import com.example.domain.domainErrors.Result
import com.example.domain.repository.NetworkRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ObserveInternetConnectionTest {

    private val repository: NetworkRepository = mockk()
    private lateinit var useCase: ObserveInternetConnection

    @Before
    fun setUp() {
        useCase = ObserveInternetConnection(repository)
    }

    @Test
    fun `invoke returns flow from repository`() = runTest {
        val expectedFlow = flowOf(
            Result.Success<Boolean, NetworkError>(true)
        )

        every { repository.observeNetwork() } returns expectedFlow

        val result = useCase().first()

        assert(result is Result.Success)
        assert((result as Result.Success).data)

        verify(exactly = 1) { repository.observeNetwork() }
    }

    @Test
    fun `invoke propagates error from repository`() = runTest {
        val expectedFlow = flowOf(
            Result.Error<Boolean, NetworkError>(NetworkError.Network.NO_INTERNET)
        )

        every { repository.observeNetwork() } returns expectedFlow

        val result = useCase().first()

        assert(result is Result.Error)
        assert((result as Result.Error).error == NetworkError.Network.NO_INTERNET)

        verify { repository.observeNetwork() }
    }
}
