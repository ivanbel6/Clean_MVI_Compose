package com.example.clean_mvi_compose.ui.main

import android.os.Bundle
import android.util.Log
import com.example.clean_mvi_compose.R
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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



            Log.v("TestInternetConnection" , uiState.toString())



            Clean_MVI_ComposeTheme(darkTheme = uiState.isDarkTheme) {
                Surface(modifier = Modifier.fillMaxSize()) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        ThemeSwitcher(
                            isDarkTheme = uiState.isDarkTheme,
                            onToggle = viewModel::handleIntent
                        )

                        HomeScreen(
                            vmIntent = viewModel::handleIntent
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
    onToggle: (MainIntent) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(
                id = if (isDarkTheme) R.string.dark_theme else R.string.light_theme
            ),
            style = MaterialTheme.typography.titleMedium
        )

        Switch(
            checked = isDarkTheme, onCheckedChange = { onToggle(MainIntent.ToggleTheme(it)) })
    }
}
