package com.example.clean_mvi_compose.ui.onboarding

import androidx.compose.runtime.Composable

@Composable
fun OnboardingScreen2() {
    OnboardingPage(
        imageUrl = "https://images.unsplash.com/photo-1519681393784-d120267933ba?auto=format&fit=crop&q=80&w=1974",
        title = "Только для тебя.",
        subtitle = "Всё хранится локально.\nЗащищено PIN-кодом или биометрией.\nЗдесь можно быть честным."
    )
}
