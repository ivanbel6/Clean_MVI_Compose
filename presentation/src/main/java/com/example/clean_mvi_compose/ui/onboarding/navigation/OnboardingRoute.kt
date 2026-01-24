package com.example.clean_mvi_compose.ui.onboarding.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.clean_mvi_compose.ui.main.navigation.Route
import com.example.clean_mvi_compose.ui.onboarding.OnboardingScreen1
import com.example.clean_mvi_compose.ui.onboarding.OnboardingScreen2
import com.example.clean_mvi_compose.ui.onboarding.OnboardingScreen3
import kotlinx.coroutines.launch

@Composable
fun OnboardingRoute(navController: NavHostController) {
    val pageCount = 3
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val coroutineScope = rememberCoroutineScope()

    val isLastPage by remember { derivedStateOf { pagerState.currentPage == pageCount - 1 } }

    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            pageSpacing = 0.dp,
            contentPadding = PaddingValues(0.dp)
        ) { page ->
            when (page) {
                0 -> OnboardingScreen1()
                1 -> OnboardingScreen2()
                2 -> OnboardingScreen3()
                else -> Box(Modifier.fillMaxSize().background(Color.Black))
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.55f),
                            Color.Black.copy(alpha = 0.78f)
                        )
                    )
                )
                .padding(horizontal = 28.dp)
                .padding(top = 32.dp, bottom = 20.dp + bottomPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            navController.navigate(Route.HomeScreen) {
                                popUpTo(Route.Onboarding) { inclusive = true }
                            }
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.White.copy(alpha = 0.75f)
                    )
                ) {
                    Text("Пропустить", style = MaterialTheme.typography.labelLarge)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    repeat(pageCount) { index ->
                        val active = index == pagerState.currentPage
                        val size by animateDpAsState(
                            targetValue = if (active) 10.dp else 6.dp,
                            animationSpec = tween(380), label = ""
                        )

                        Box(
                            modifier = Modifier
                                .size(size)
                                .clip(CircleShape)
                                .background(
                                    if (active) Color.White else Color.White.copy(alpha = 0.40f)
                                )
                        )
                    }
                }
            }

            // CTA кнопка
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (isLastPage) {
                            navController.navigate(Route.HomeScreen) {
                                popUpTo(Route.Onboarding) { inclusive = true }
                            }
                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isLastPage)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.92f),
                    contentColor = if (isLastPage)
                        MaterialTheme.colorScheme.onPrimary
                    else
                        MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text(
                    text = if (isLastPage) "Начать" else "Далее",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}