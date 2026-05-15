package com.kumbarakala.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth: FirebaseAuth? = runCatching { FirebaseAuth.getInstance() }.getOrNull()

    val currentUserId: String
        get() = auth?.currentUser?.uid ?: "demo-user"

    suspend fun login(email: String, password: String): Result<Unit> = runCatching {
        val firebaseAuth = auth ?: return@runCatching
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun register(email: String, password: String): Result<Unit> = runCatching {
        val firebaseAuth = auth ?: return@runCatching
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }
}
