package com.example.clean_mvi_compose.ui.main.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clean_mvi_compose.ui.homePage.HomeScreen
import com.example.clean_mvi_compose.ui.main.AppIntent
import com.example.clean_mvi_compose.ui.main.AppUiState
import com.example.clean_mvi_compose.ui.registration.RegistrationIntent
import com.example.clean_mvi_compose.ui.registration.RegistrationScreen
import com.example.clean_mvi_compose.ui.registration.RegistrationState
import com.example.clean_mvi_compose.ui.settings.SettingsScreen


@Composable
fun AppNavHost(
    appUiState: AppUiState,
    registrationUiState: RegistrationState,
    appOnIntent: (AppIntent) -> Unit,
    registrationOnIntent: (RegistrationIntent) -> Unit,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable<Route.HomeScreen> {
            HomeScreen(
                uiState = appUiState,
                onItemClick = {},
                onNavigateToSecond = {},
                onNavigateToSettings = { navController.navigate(Route.SettingsPage) },
                onNavigateToRegistration = { navController.navigate(Route.RegistrationPage) },
                vmIntent = appOnIntent
            )
        }

        composable<Route.SettingsPage> {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable<Route.RegistrationPage> {
            RegistrationScreen(
                state = registrationUiState,
                onIntent = registrationOnIntent,
                onBackClick = { navController.popBackStack() }
            )

        }
    }

}