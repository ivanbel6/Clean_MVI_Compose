package com.example.clean_mvi_compose.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.clean_mvi_compose.ui.main.navigation.AppNavHost
import com.example.clean_mvi_compose.ui.registration.RegistrationViewModel
import com.example.clean_mvi_compose.ui.theme.Clean_MVI_ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val appViewModel: AppViewModel = hiltViewModel()
            val appUiState by appViewModel.uiState.collectAsStateWithLifecycle()

            val registrationViewModel : RegistrationViewModel = hiltViewModel()
            val registrationUiState by registrationViewModel.state.collectAsStateWithLifecycle()

            Log.v("TestInternetConnection", appUiState.toString())

            Clean_MVI_ComposeTheme(darkTheme = appUiState.isDarkTheme) {
                AppNavHost(
                    appUiState = appUiState,
                    appOnIntent = appViewModel::handleIntent,
                    registrationUiState = registrationUiState,
                    registrationOnIntent = registrationViewModel::onIntent,
                )
            }
        }
    }
}

