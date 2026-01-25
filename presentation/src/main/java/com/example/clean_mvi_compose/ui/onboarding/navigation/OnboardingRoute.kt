package com.example.clean_mvi_compose.ui.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.clean_mvi_compose.ui.main.navigation.Route
import com.example.clean_mvi_compose.ui.onboarding.OnboardingScreen

@Composable
fun OnboardingRoute(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    OnboardingScreen(
        onComplete = {
            navController.navigate(Route.HomeScreen) {
                popUpTo(Route.Onboarding) { inclusive = true }
            }
        },
        onSkip = {
            navController.navigate(Route.HomeScreen) {
                popUpTo(Route.Onboarding) { inclusive = true }
            }
        },
        modifier = modifier
    )
}