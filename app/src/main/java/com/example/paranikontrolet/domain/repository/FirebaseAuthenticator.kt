package com.example.paranikontrolet.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthenticator {

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult>

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult>

    suspend fun signOut()

    suspend fun getCurrentUser(): Boolean

    suspend fun getCurrentUserInfo(): FirebaseUser?

    suspend fun forgotPassword(email: String): Task<Void>
}