package com.example.clean_mvi_compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.clean_mvi_compose.entities.SpaceItem
import com.example.clean_mvi_compose.ui.homePage.HomeScreen
import com.example.clean_mvi_compose.ui.theme.Clean_MVI_ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = hiltViewModel()

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()


            LaunchedEffect(Unit) {
                viewModel.handleIntent(MainIntent.LoadTheme)
            }

            Clean_MVI_ComposeTheme(darkTheme = uiState.isDarkTheme) {
                Surface(modifier = Modifier.fillMaxSize()) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        ThemeSwitcher(
                            isDarkTheme = uiState.isDarkTheme, onToggle = {
                                viewModel.handleIntent(
                                    MainIntent.ToggleTheme(
                                        it
                                    )
                                )
                            })


                        HomeScreen(
                            listOf(
                                SpaceItem(
                                    "Earth",
                                    "Our home planet, the third from the Sun.",
                                    "https://images-assets.nasa.gov/image/PIA18033/PIA18033~medium.jpg"
                                ), SpaceItem(
                                    "Mars",
                                    "The red planet, known for its iron oxide surface.",
                                    "https://images-assets.nasa.gov/image/PIA00407/PIA00407~medium.jpg"
                                ), SpaceItem(
                                    "Jupiter",
                                    "The gas giant with a massive storm called the Great Red Spot.",
                                    "https://images-assets.nasa.gov/image/PIA21775/PIA21775~medium.jpg"
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeSwitcher(
    isDarkTheme: Boolean,
    onToggle: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (isDarkTheme) "Тёмная тема" else "Светлая тема",
            style = MaterialTheme.typography.titleMedium
        )

        Switch(
            checked = isDarkTheme, onCheckedChange = { onToggle(it) })
    }
}
