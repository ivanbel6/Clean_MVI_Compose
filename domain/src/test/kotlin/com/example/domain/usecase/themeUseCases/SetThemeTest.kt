package com.example.domain.usecase.themeUseCases

import com.example.domain.domainErrors.Result
import com.example.domain.domainErrors.ThemeError
import com.example.domain.repository.ThemeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SetThemeTest {

    private val repository: ThemeRepository = mockk(relaxed = false)
    private lateinit var setTheme: SetTheme

    @Before
    fun setup() {
        setTheme = SetTheme(repository)
    }

    @Test
    fun `when repository returns Success then use case returns Success`() = runTest {
        // GIVEN
        coEvery { repository.setTheme(true) } returns Result.Success(Unit)

        // WHEN
        val result = setTheme(true)

        // THEN
        when (result) {
            is Result.Success -> {
                // Unit — there is no data, the fact of success has already
                // been verified.
            }
            else -> fail("Expected Result.Success, but was $result")
        }

        coVerify(exactly = 1) { repository.setTheme(true) }
        confirmVerified(repository)
    }

    @Test
    fun `when repository returns Error then use case returns same Error`() = runTest {
        // GIVEN
        val expectedError = ThemeError.Errors.SET_THEME_ERROR
        coEvery { repository.setTheme(false) } returns Result.Error(expectedError)

        // WHEN
        val result = setTheme(false)

        // THEN
        when (result) {
            is Result.Error -> {
                assertEquals(expectedError, result.error)
            }
            else -> fail("Expected Result.Error, but was $result")
        }

        coVerify(exactly = 1) { repository.setTheme(false) }
        confirmVerified(repository)
    }
}
