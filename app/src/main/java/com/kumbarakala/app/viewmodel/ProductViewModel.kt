package com.kumbarakala.app.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumbarakala.app.data.model.Product
import com.kumbarakala.app.data.repository.ProductRepository
import com.kumbarakala.app.data.repository.SampleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductUiState(
    val products: List<Product> = emptyList(),
    val selectedProduct: Product? = null,
    val query: String = "",
    val selectedCategory: String = "All",
    val isLoading: Boolean = true,
    val error: String? = null,
    val healthTip: String = ""
) {
    val categories: List<String> = SampleData.categories

    val visibleProducts: List<Product>
        get() = products.filter { product ->
            val categoryMatches = selectedCategory == "All" || product.category == selectedCategory
            val queryMatches = query.isBlank() ||
                product.name.contains(query, ignoreCase = true) ||
                product.villageName.contains(query, ignoreCase = true) ||
                product.artisanName.contains(query, ignoreCase = true)
            categoryMatches && queryMatches
        }
}

class ProductViewModel(
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, error = null) }
        val products = runCatching { repository.getProducts() }
            .onFailure { error -> _uiState.update { it.copy(error = error.message) } }
            .getOrDefault(SampleData.products)
        _uiState.update {
            it.copy(
                products = products,
                isLoading = false,
                healthTip = generateHealthTip(products.firstOrNull())
            )
        }
    }

    fun search(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun selectProduct(id: String) = viewModelScope.launch {
        val product = repository.getProductById(id) ?: _uiState.value.products.firstOrNull { it.id == id }
        _uiState.update {
            it.copy(selectedProduct = product, healthTip = generateHealthTip(product))
        }
    }

    fun toggleFavorite(product: Product) = viewModelScope.launch {
        val updated = repository.toggleFavorite(product, "demo-user")
        _uiState.update { state ->
            state.copy(
                products = state.products.map { if (it.id == product.id) updated else it },
                selectedProduct = if (state.selectedProduct?.id == product.id) updated else state.selectedProduct
            )
        }
    }

    fun addProduct(product: Product, imageUri: Uri?) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, error = null) }
        val imageUrl = imageUri?.let { repository.uploadProductImage(it).getOrNull() }.orEmpty()
        val newProduct = product.copy(
            id = product.id.ifBlank { "local-${System.currentTimeMillis()}" },
            imageUrl = imageUrl.ifBlank { product.imageUrl }
        )
        repository.addProduct(newProduct)
        _uiState.update {
            it.copy(products = listOf(newProduct) + it.products, isLoading = false)
        }
    }

    private fun generateHealthTip(product: Product?): String {
        val name = product?.name ?: "clay products"
        return "AI tip: Use $name as part of a low-plastic kitchen routine and clean it gently with warm water to preserve the natural clay surface."
    }
}
