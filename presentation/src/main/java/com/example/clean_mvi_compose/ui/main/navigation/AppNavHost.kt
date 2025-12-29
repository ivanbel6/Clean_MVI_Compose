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
import com.example.clean_mvi_compose.ui.settings.SecondPage


@Composable
fun AppNavHost(
    uiState: AppUiState,
    onIntent: (AppIntent) -> Unit,
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
                uiState = uiState,
                onButtonClick = { navController.navigate(Route.SettingsPage) },
                vmIntent = onIntent
            )
        }

        composable<Route.SettingsPage> {
            SecondPage(
                onButtonClick = { navController.popBackStack() }
            )
        }
    }
}