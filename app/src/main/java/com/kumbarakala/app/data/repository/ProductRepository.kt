package com.kumbarakala.app.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import com.kumbarakala.app.data.model.Product
import kotlinx.coroutines.tasks.await
import java.util.UUID

class ProductRepository {
    private val firestore: FirebaseFirestore? = runCatching {
        FirebaseFirestore.getInstance().apply {
            firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
        }
    }.getOrNull()

    private val storage: FirebaseStorage? = runCatching { FirebaseStorage.getInstance() }.getOrNull()

    suspend fun getProducts(): List<Product> {
        val remote = runCatching {
            firestore?.collection("products")?.get()?.await()?.documents
                ?.mapNotNull { document -> document.toObject(Product::class.java)?.copy(id = document.id) }
                .orEmpty()
        }.getOrDefault(emptyList())
        return remote.ifEmpty { SampleData.products }
    }

    suspend fun getProductById(id: String): Product? {
        getProducts().firstOrNull { it.id == id }?.let { return it }
        return runCatching {
            firestore?.collection("products")?.document(id)?.get()?.await()
                ?.toObject(Product::class.java)?.copy(id = id)
        }.getOrNull()
    }

    suspend fun addProduct(product: Product): Result<Unit> = runCatching {
        val db = firestore ?: error("Firebase is not configured. Add app/google-services.json.")
        val id = product.id.ifBlank { db.collection("products").document().id }
        db.collection("products").document(id).set(product.copy(id = id)).await()
    }

    suspend fun uploadProductImage(uri: Uri): Result<String> = runCatching {
        val bucket = storage ?: error("Firebase Storage is not configured.")
        val path = "product_images/${UUID.randomUUID()}.jpg"
        val ref = bucket.reference.child(path)
        ref.putFile(uri).await()
        ref.downloadUrl.await().toString()
    }

    suspend fun toggleFavorite(product: Product, userId: String): Product {
        val updated = product.copy(isFavorite = !product.isFavorite)
        runCatching {
            val db = firestore ?: return@runCatching
            val favoriteRef = db.collection("users").document(userId)
                .collection("favorites").document(product.id)
            if (updated.isFavorite) favoriteRef.set(updated).await() else favoriteRef.delete().await()
        }
        return updated
    }

    suspend fun getFavoriteProducts(userId: String): List<Product> = runCatching {
        firestore?.collection("users")?.document(userId)?.collection("favorites")?.get()?.await()
            ?.documents?.mapNotNull { it.toObject(Product::class.java)?.copy(id = it.id, isFavorite = true) }
            .orEmpty()
    }.getOrDefault(emptyList())
}
