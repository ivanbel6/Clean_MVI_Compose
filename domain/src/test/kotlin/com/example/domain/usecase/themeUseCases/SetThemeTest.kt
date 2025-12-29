package com.example.domain.usecase.themeUseCases

import com.example.domain.domainErrors.Result
import com.example.domain.domainErrors.ThemeError
import com.example.domain.repository.ThemeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SetThemeTest {

    private lateinit var repository: ThemeRepository
    private lateinit var setTheme: SetTheme

    @Before
    fun setup() {
        repository = mockk()
        setTheme = SetTheme(repository)
    }

    @Test
    fun `invoke returns Success when repository succeeds`() = runTest {
        // arrange
        coEvery { repository.setTheme(true) } returns Result.Success(Unit)

        // act
        val result = setTheme(true)

        // assert
        assertTrue(result is Result.Success)
        coVerify(exactly = 1) { repository.setTheme(true) }
    }

    @Test
    fun `invoke returns Error when repository fails`() = runTest {
        // arrange
        coEvery { repository.setTheme(false) } returns Result.Error(ThemeError.Errors.SET_THEME_ERROR)

        // act
        val result = setTheme(false)

        // assert
        assertTrue(result is Result.Error)
        coVerify(exactly = 1) { repository.setTheme(false) }
    }
}

