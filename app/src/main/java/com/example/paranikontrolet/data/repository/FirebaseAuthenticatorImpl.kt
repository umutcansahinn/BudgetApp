package com.example.paranikontrolet.data.repository

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthenticatorImpl @Inject constructor(
    private val auth : FirebaseAuth
): FirebaseAuthenticator {

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult {
        return  auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult {
       return auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun getCurrentUser() : Boolean {
        return auth.currentUser != null
    }

    override suspend fun getCurrentUserInfo(): FirebaseUser? {
        return auth.currentUser
    }

}