package com.example.clean_mvi_compose.ui.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.clean_mvi_compose.entities.SpaceItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    items: List<SpaceItem>,
    onItemClick: (SpaceItem) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🪐 CosmoExplorer") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp)
        ) {
            items(items) { item ->
                SpaceCard(item, onItemClick)
            }
        }
    }
}

@Composable
fun SpaceCard(item: SpaceItem, onClick: (SpaceItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
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
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val mockData = listOf(
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
    HomeScreen(items = mockData)
}
