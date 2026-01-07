package com.example.domain.usecase.themeUseCases

import com.example.domain.domainErrors.Result
import com.example.domain.domainErrors.ThemeError
import com.example.domain.repository.ThemeRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ObserveThemeTest {

    private val repository: ThemeRepository = mockk()
    private lateinit var useCase: ObserveTheme

    @Before
    fun setUp() {
        useCase = ObserveTheme(repository)
    }

    @Test
    fun `invoke returns theme flow from repository`() = runTest {
        val expectedFlow = flowOf(
            Result.Success<Boolean, ThemeError>(false)
        )

        every { repository.observeTheme() } returns expectedFlow

        val result = useCase().first()

        assert(result is Result.Success)
        assert(!(result as Result.Success).data)

        verify(exactly = 1) { repository.observeTheme() }
    }

    @Test
    fun `invoke propagates theme error`() = runTest {
        val expectedFlow = flowOf(
            Result.Error<Boolean, ThemeError>(ThemeError.Errors.GET_THEME_ERROR)
        )

        every { repository.observeTheme() } returns expectedFlow

        val result = useCase().first()

        assert(result is Result.Error)
        assert((result as Result.Error).error == ThemeError.Errors.GET_THEME_ERROR)

        verify { repository.observeTheme() }
    }
}
