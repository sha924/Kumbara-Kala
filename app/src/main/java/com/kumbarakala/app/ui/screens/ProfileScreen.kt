package com.kumbarakala.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kumbarakala.app.data.model.Product
import com.kumbarakala.app.data.repository.SampleData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(products: List<Product>) {
    val artisan = SampleData.artisan
    Scaffold(topBar = { TopAppBar(title = { Text("Artisan Profile") }) }) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        AsyncImage(
                            model = artisan.photoUrl,
                            contentDescription = artisan.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(160.dp)
                                .fillMaxWidth()
                                .clip(CircleShape)
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(artisan.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Text(artisan.village)
                        Text("${artisan.productsCreated} products created", color = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.height(10.dp))
                        Text(artisan.heritageStory)
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text("Products Created", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
            items(products.take(4), key = { it.id }) { product ->
                Card(Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                    Column(Modifier.padding(14.dp)) {
                        Text(product.name, fontWeight = FontWeight.Bold)
                        Text(product.description, maxLines = 2)
                    }
                }
            }
        }
    }
}
