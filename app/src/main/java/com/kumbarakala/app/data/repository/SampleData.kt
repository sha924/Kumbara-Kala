package com.kumbarakala.app.data.repository

import com.kumbarakala.app.data.model.Artisan
import com.kumbarakala.app.data.model.Product

object SampleData {
    val products = listOf(
        Product(
            id = "sample-pot",
            name = "Clay Water Pot",
            description = "A naturally cooling handmade matka for clean, earthy-tasting drinking water.",
            price = 899.0,
            imageUrl = "https://images.unsplash.com/photo-1578749556568-bc2c40e68b61?auto=format&fit=crop&w=900&q=80",
            category = "Waterware",
            healthBenefits = "Porous clay supports natural cooling and may help maintain mineral balance.",
            ecoBenefits = "Reusable, biodegradable, and made with low-energy traditional firing.",
            artisanName = "Ramesh Kumbhar",
            villageName = "Molela"
        ),
        Product(
            id = "sample-cups",
            name = "Terracotta Tea Cups",
            description = "Rustic kulhad-style cups that add a warm clay aroma to tea and coffee.",
            price = 349.0,
            imageUrl = "https://images.unsplash.com/photo-1610701596007-11502861dcfa?auto=format&fit=crop&w=900&q=80",
            category = "Drinkware",
            healthBenefits = "Unglazed clay avoids plastic contact and keeps beverages pleasantly warm.",
            ecoBenefits = "Plastic-free serving option crafted from natural earth.",
            artisanName = "Meera Prajapati",
            villageName = "Khurja"
        ),
        Product(
            id = "sample-diya",
            name = "Handmade Diya Set",
            description = "Festival-ready diyas shaped and finished by hand for homes and ceremonies.",
            price = 199.0,
            imageUrl = "https://images.unsplash.com/photo-1605292356183-a77d0a9c9d1d?auto=format&fit=crop&w=900&q=80",
            category = "Decor",
            healthBenefits = "Natural clay and cotton-wick lighting creates a gentle traditional ambience.",
            ecoBenefits = "Compostable alternative to synthetic festive decor.",
            artisanName = "Kavita Kumari",
            villageName = "Dharavi"
        ),
        Product(
            id = "sample-cookpot",
            name = "Clay Cooking Pot",
            description = "Slow-cooking handi designed for curries, dals, and heritage recipes.",
            price = 1299.0,
            imageUrl = "https://images.unsplash.com/photo-1592187433556-84d7a859f0f7?auto=format&fit=crop&w=900&q=80",
            category = "Cookware",
            healthBenefits = "Slow heat distribution helps preserve moisture and flavor with less oil.",
            ecoBenefits = "Long-lasting natural cookware that reduces metal and coating waste.",
            artisanName = "Sundar Lal",
            villageName = "Nizamabad"
        ),
        Product(
            id = "sample-vase",
            name = "Decorative Terracotta Vase",
            description = "A gallery-style vase with hand-etched details inspired by village motifs.",
            price = 1599.0,
            imageUrl = "https://images.unsplash.com/photo-1565193566173-7a0ee3dbe261?auto=format&fit=crop&w=900&q=80",
            category = "Decor",
            healthBenefits = "Natural materials bring a calm, grounded feel to living spaces.",
            ecoBenefits = "Made from locally sourced clay with minimal synthetic inputs.",
            artisanName = "Anita Devi",
            villageName = "Molela"
        )
    )

    val artisan = Artisan(
        id = "artisan-sample",
        name = "Meera Prajapati",
        village = "Khurja, Uttar Pradesh",
        photoUrl = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&w=600&q=80",
        heritageStory = "Meera belongs to a family of potters who have shaped clay for four generations. Her work blends everyday utility with hand-painted motifs taught by elders in her village.",
        productsCreated = products.size
    )

    val categories = listOf("All", "Waterware", "Drinkware", "Cookware", "Decor")
}
