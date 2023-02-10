package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase

import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.utils.Constants
import java.util.*
import javax.inject.Inject

class SaveBudget @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {

    suspend operator fun invoke(
        amount: Float?,
        isIncome: Boolean?,
        type: String?,
        date: Date?,
        currentUserId: String?
    ) {

        if (
            amount != null &&
            isIncome != null &&
            type != null &&
            date != null &&
            currentUserId != null
        ) {
            val hashMap = hashMapOf<String, Any>(
                Constants.AMOUNT to amount,
                Constants.IS_INCOME to isIncome,
                Constants.TYPE to type,
                Constants.DATE to date.time,
                Constants.USER_ID to currentUserId
            )
            firestore.saveBudget(hashMap = hashMap)
        }
    }
}