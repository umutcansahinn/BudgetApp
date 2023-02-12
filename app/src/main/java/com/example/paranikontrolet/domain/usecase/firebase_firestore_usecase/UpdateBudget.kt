package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase

import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class UpdateBudget @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {

    suspend operator fun invoke(hashMap: HashMap<String,Any>,documentId: String): Task<Void> {
        return firestore.updateBudget(hashMap = hashMap, documentId = documentId)
    }
}
