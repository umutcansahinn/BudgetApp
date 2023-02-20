package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.save_budget

import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.utils.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import java.util.*
import javax.inject.Inject

class SaveBudgetImpl @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) : SaveBudget {

    override suspend operator fun invoke(
        amount: Float,
        isIncome: Boolean,
        type: String,
        date: Date,
        currentUserId: String
    ): Task<DocumentReference> {

        val hashMap = hashMapOf<String, Any>(
            Constants.AMOUNT to amount,
            Constants.IS_INCOME to isIncome,
            Constants.TYPE to type,
            Constants.DATE to date.time,
            Constants.USER_ID to currentUserId
        )
        return firestore.saveBudget(hashMap = hashMap)

    }
}