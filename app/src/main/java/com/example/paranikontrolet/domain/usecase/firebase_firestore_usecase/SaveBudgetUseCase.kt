package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase

import android.util.Log
import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.domain.usecase.FirebaseAuthUseCases
import java.util.*
import javax.inject.Inject

class SaveBudgetUseCase @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {

    suspend operator fun invoke(
        amount: Float?,
        isIncome: Boolean?,
        isRegular: Boolean?,
        type: String?,
        date: Date?,
        currentUserId: String?
    ) {

        if (
            amount != null &&
            isIncome != null &&
            isRegular != null &&
            type != null &&
            date != null &&
            currentUserId != null
        ) {
            firestore.saveBudget(
                amount = amount,
                isIncome = isIncome,
                isRegular = isRegular,
                type = type,
                date = date,
                currentUserId = currentUserId
            )
            Log.d("result", "useCase ok")
        }
    }
}