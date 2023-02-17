package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase

import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.utils.Constants
import com.google.android.gms.tasks.Task
import java.util.*
import javax.inject.Inject

class UpdateBudget @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {

    suspend operator fun invoke(
        amount: Float,
        isIncome: Boolean,
        type: String,
        date: Date,
        currentUserId: String,
        documentId: String
    ): Task<Void> {

        val hashMap = hashMapOf<String, Any>(
            Constants.AMOUNT to amount,
            Constants.IS_INCOME to isIncome,
            Constants.TYPE to type,
            Constants.DATE to date.time,
            Constants.USER_ID to currentUserId
        )
        return firestore.updateBudget(hashMap = hashMap, documentId = documentId)
    }
}
