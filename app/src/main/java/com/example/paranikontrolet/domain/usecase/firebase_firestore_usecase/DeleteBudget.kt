package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase

import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class DeleteBudget @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {

    suspend operator fun invoke(documentId: String): Task<Void> {
        return firestore.deleteBudget(documentId = documentId)
    }
}