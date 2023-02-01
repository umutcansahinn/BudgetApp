package com.example.paranikontrolet.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthenticator {

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult

    suspend fun signOut()

    suspend fun getCurrentUser(): Boolean

    suspend fun getCurrentUserInfo(): FirebaseUser?
}