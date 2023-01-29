package com.example.paranikontrolet.domain.repository

import com.google.firebase.auth.AuthResult

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
}