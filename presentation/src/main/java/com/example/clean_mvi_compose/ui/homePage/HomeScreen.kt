package com.example.clean_mvi_compose.ui.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.clean_mvi_compose.R
import com.example.clean_mvi_compose.entities.SpaceItem
import com.example.clean_mvi_compose.ui.main.AppIntent
import com.example.clean_mvi_compose.ui.main.AppUiState
import com.example.clean_mvi_compose.ui.theme.Clean_MVI_ComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: AppUiState,
    onItemClick: (SpaceItem) -> Unit,
    onNavigateToSecond: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToRegistration: () -> Unit,
    vmIntent: (AppIntent) -> Unit,
) {

    val items = rememberSpaceItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CosmoExplorer") },
                actions = {
                    IconButton(onClick = onNavigateToRegistration) {
                        Icon(
                            imageVector = Icons.Default.PersonAdd,
                            contentDescription = "Registration"
                        )
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            ThemeSwitcher(
                isDarkTheme = uiState.isDarkTheme,
                onToggle = vmIntent
            )

            Button(
                onClick = onNavigateToSecond,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Navigate to Second Page")
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { item ->
                    SpaceCard(item = item, onClick = onItemClick)
                }
            }
        }
    }
}

@Composable
fun SpaceCard(
    item: SpaceItem,
    onClick: (SpaceItem) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        onClick = { onClick(item) }
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
fun ThemeSwitcher(
    isDarkTheme: Boolean,
    onToggle: (AppIntent) -> Unit,
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
            checked = isDarkTheme,
            onCheckedChange = { onToggle(AppIntent.ToggleTheme(it)) }
        )
    }
}

@Composable
private fun rememberSpaceItems(): List<SpaceItem> =
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

@Preview(showBackground = true)
@Composable
fun HomeScreenLightPreview() {
    Clean_MVI_ComposeTheme(darkTheme = false) {
        HomeScreen(
            uiState = AppUiState(),
            onItemClick = {},
            onNavigateToSecond = {},
            onNavigateToSettings = {},
            onNavigateToRegistration = {},
            vmIntent = {}
        )
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenDarkPreview() {
    Clean_MVI_ComposeTheme(darkTheme = true) {
        HomeScreen(
            uiState = AppUiState(),
            onItemClick = {},
            onNavigateToSecond = {},
            onNavigateToSettings = {},
            onNavigateToRegistration = {},
            vmIntent = {}
        )
    }

}
