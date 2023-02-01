package com.example.paranikontrolet.domain.repository

import com.google.firebase.firestore.QuerySnapshot

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
    suspend fun getBudgetDocuments(): QuerySnapshot

}