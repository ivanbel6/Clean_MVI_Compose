package com.example.clean_mvi_compose.ui.onboarding.model

import com.example.clean_mvi_compose.R

object OnboardingPages {
    val all = listOf(
        OnboardingPageData(
            lightImage = R.drawable.onboarding_screen1_lighttheme,
            darkImage = R.drawable.onboarding_screen1_darktheme,
            title = "Твой день.\nБез фильтров.",
            subtitle = "Просто отмечай, что сделал.\nЧто провалил.\nИ что хочешь изменить завтра."
        ),
        OnboardingPageData(
            lightImage = R.drawable.onboarding_screen1_lighttheme,
            darkImage = R.drawable.onboarding_screen1_darktheme,
            title = "Только для тебя.",
            subtitle = "Всё хранится локально.\nЗащищено PIN-кодом или биометрией.\nЗдесь можно быть честным."
        ),
        OnboardingPageData(
            lightImage = R.drawable.onboarding_screen1_lighttheme,
            darkImage = R.drawable.onboarding_screen1_darktheme,
            title = "Маленькие шаги.\nБольшая картина.",
            subtitle = "Со временем ты увидишь закономерности.\nСвои настоящие привычки.\nИ точки роста."
        )
    )
}