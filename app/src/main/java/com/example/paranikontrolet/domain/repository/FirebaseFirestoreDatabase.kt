package com.example.paranikontrolet.domain.repository

import com.google.firebase.firestore.QuerySnapshot
import java.util.Date

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
        type: String,
        date: Date,
        currentUserId: String
    )
    suspend fun getBudgetDocuments(userId: String): QuerySnapshot

}