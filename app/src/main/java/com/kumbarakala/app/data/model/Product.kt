package com.kumbarakala.app.data.model

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val category: String = "",
    val healthBenefits: String = "",
    val ecoBenefits: String = "",
    val artisanName: String = "",
    val villageName: String = "",
    val isFavorite: Boolean = false
)
