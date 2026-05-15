package com.kumbarakala.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumbarakala.app.data.model.Product
import com.kumbarakala.app.ui.components.CategoryChips
import com.kumbarakala.app.ui.components.KumbaraSearchBar
import com.kumbarakala.app.ui.components.ProductCard
import com.kumbarakala.app.ui.theme.Cream
import com.kumbarakala.app.ui.theme.Sand
import com.kumbarakala.app.viewmodel.ProductUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: ProductUiState,
    onSearch: (String) -> Unit,
    onCategory: (String) -> Unit,
    onProductClick: (Product) -> Unit,
    onFavorite: (Product) -> Unit,
    onUploadClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Cream, Sand, MaterialTheme.colorScheme.background)))
    ) {
        TopAppBar(
            title = {
                Column {
                    Text("Kumbara-Kala", fontWeight = FontWeight.Bold)
                    Text("Digital branding for potters", style = MaterialTheme.typography.bodySmall)
                }
            },
            actions = {
                Button(onClick = onUploadClick, contentPadding = PaddingValues(horizontal = 12.dp)) {
                    Icon(Icons.Filled.Add, contentDescription = "Upload")
                    Spacer(Modifier.width(4.dp))
                    Text("Upload")
                }
            }
        )
        LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
            item {
                KumbaraSearchBar(
                    query = state.query,
                    onQueryChange = onSearch,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            item {
                CategoryChips(state.categories, state.selectedCategory, onCategory)
            }
            item {
                SectionTitle("Featured Clay Products")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(state.visibleProducts.take(5)) { product ->
                        ProductCard(
                            product = product,
                            onClick = { onProductClick(product) },
                            onFavorite = { onFavorite(product) },
                            modifier = Modifier.width(250.dp)
                        )
                    }
                }
            }
            item {
                SectionTitle("Artisan Health Tip")
                Text(
                    text = state.healthTip,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Row(Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    }
}
