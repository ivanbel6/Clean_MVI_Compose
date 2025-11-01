package com.example.clean_mvi_compose.ui.main

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.clean_mvi_compose.entities.SpaceItem
import com.example.clean_mvi_compose.ui.homePage.HomeScreen
import com.example.clean_mvi_compose.ui.theme.Clean_MVI_ComposeTheme


class MainActivity : ComponentActivity() {
    companion object {
    }


//    private val Context.dataStore by preferencesDataStore(name = "settings")
//
//    private val darkModeKey = booleanPreferencesKey()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Clean_MVI_ComposeTheme {
                HomeScreen(
                    listOf(
                        SpaceItem(
                            "Earth",
                            "Our home planet, the third from the Sun.",
                            "https://images-assets.nasa.gov/image/PIA18033/PIA18033~medium.jpg"
                        ),
                        SpaceItem(
                            "Mars",
                            "The red planet, known for its iron oxide surface.",
                            "https://images-assets.nasa.gov/image/PIA00407/PIA00407~medium.jpg"
                        ),
                        SpaceItem(
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

