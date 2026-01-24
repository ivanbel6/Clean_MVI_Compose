package com.example.clean_mvi_compose.ui.onboarding

import androidx.compose.runtime.Composable

@Composable
fun OnboardingScreen3() {
    OnboardingPage(
        imageUrl = "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&q=80&w=1974",
        title = "Маленькие шаги.\nБольшая картина.",
        subtitle = "Со временем ты увидишь закономерности.\nСвои настоящие привычки.\nИ точки роста."
    )
}
