package com.example.clean_mvi_compose.ui.onboarding

import androidx.compose.runtime.Composable

@Composable
fun OnboardingScreen1() {
    OnboardingPage(
        imageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&q=80&w=1974",
        title = "Твой день.\nБез фильтров.",
        subtitle = "Просто отмечай, что сделал.\nЧто провалил.\nИ что хочешь изменить завтра."
    )
}
