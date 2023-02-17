package com.example.paranikontrolet.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
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
        hashMap: HashMap<String,Any>
    ): Task<DocumentReference>
    suspend fun getBudgetDocuments(userId: String): QuerySnapshot

    suspend fun deleteBudget(documentId: String): Task<Void>
    suspend fun updateBudget(hashMap: HashMap<String,Any>,documentId: String): Task<Void>

}