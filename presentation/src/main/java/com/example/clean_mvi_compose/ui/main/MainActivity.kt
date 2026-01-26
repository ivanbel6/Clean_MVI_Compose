package com.example.clean_mvi_compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.clean_mvi_compose.ui.main.navigation.AppNavHost
import com.example.clean_mvi_compose.ui.theme.Clean_MVI_ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Clean_MVI_ComposeTheme {
                AppNavHost()
            }
        }
    }
}

