package com.kumbarakala.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kumbarakala.app.data.model.Product
import com.kumbarakala.app.ui.components.CategoryChips
import com.kumbarakala.app.ui.components.KumbaraSearchBar
import com.kumbarakala.app.ui.components.ProductCard
import com.kumbarakala.app.viewmodel.ProductUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    state: ProductUiState,
    onSearch: (String) -> Unit,
    onCategory: (String) -> Unit,
    onProductClick: (Product) -> Unit,
    onFavorite: (Product) -> Unit,
    onUploadClick: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Product Catalog") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onUploadClick) {
                Icon(Icons.Filled.Add, contentDescription = "Upload product")
            }
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            KumbaraSearchBar(state.query, onSearch, Modifier.padding(16.dp))
            CategoryChips(state.categories, state.selectedCategory, onCategory)
            LazyVerticalGrid(
                columns = GridCells.Adaptive(170.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.visibleProducts, key = { it.id }) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product) },
                        onFavorite = { onFavorite(product) }
                    )
                }
            }
        }
    }
}
