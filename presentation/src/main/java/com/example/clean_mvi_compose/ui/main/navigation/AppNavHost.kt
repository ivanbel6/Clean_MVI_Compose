package com.example.clean_mvi_compose.ui.main.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clean_mvi_compose.ui.homePage.navigation.HomeRoute
import com.example.clean_mvi_compose.ui.onboarding.navigation.OnboardingRoute
import com.example.clean_mvi_compose.ui.registration.navigation.RegistrationRoute
import com.example.clean_mvi_compose.ui.settings.navigation.SettingsRoute


@Composable
fun AppNavHost() {
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

        composable<Route.Onboarding> {
            OnboardingRoute(navController)
        }

        composable<Route.HomeScreen> {
            HomeRoute(
                onNavigateToSettings = {
                    navController.navigate(Route.SettingsPage)
                },
                onNavigateToRegistration = {
                    navController.navigate(Route.RegistrationPage)
                },
                onNavigateToOnBoarding = {
                    navController.navigate(Route.Onboarding)
                }
            )
        }

        composable<Route.SettingsPage> {
            SettingsRoute(
                onBack = { navController.popBackStack() }
            )
        }

        composable<Route.RegistrationPage> {
            RegistrationRoute(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
