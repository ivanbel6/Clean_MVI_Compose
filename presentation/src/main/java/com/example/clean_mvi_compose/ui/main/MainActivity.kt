package com.example.clean_mvi_compose.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clean_mvi_compose.common.Route
import com.example.clean_mvi_compose.ui.homePage.HomeScreen
import com.example.clean_mvi_compose.ui.main.app.AppViewModel
import com.example.clean_mvi_compose.ui.secondPage.SecondPage
import com.example.clean_mvi_compose.ui.theme.Clean_MVI_ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val viewModel: AppViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            Log.v("TestInternetConnection", uiState.toString())

            Clean_MVI_ComposeTheme(darkTheme = uiState.isDarkTheme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController, startDestination = Route.HomeScreen
                    ) {
                        composable<Route.HomeScreen> {
                            HomeScreen(
                                onButtonClick = {
                                    navController.navigate(Route.SecondPage)
                                }, vmIntent = viewModel::handleIntent, uiState = uiState
                            )
                        }
                        composable<Route.SecondPage> {
                            SecondPage(
                                onButtonClick = {
                                    navController.navigate(Route.HomeScreen)
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}

