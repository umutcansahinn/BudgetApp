package com.example.paranikontrolet.domain.repository

interface FirebaseFirestoreDatabase {

    suspend fun saveUser(
        getFirebaseUserUid: String,
        email: String,
        name: String,
        password: String
    )

    suspend fun saveBudget(
        amount: Float,
        isIncome: Boolean,
        isRegular: Boolean,
        type: String
    )
}