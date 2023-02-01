package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase

import android.util.Log
import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import javax.inject.Inject

class SaveBudgetUseCase @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {

    suspend operator fun invoke(
        amount: Float?,
        isIncome: Boolean?,
        isRegular: Boolean?,
        type: String?
    ) {
        Log.d("result","inUseCase")
        Log.d("result",
        "amount: ${amount} , isIncome: ${isIncome} , isRegular: ${isRegular} , type: ${type}")
        if (
            amount != null &&
            isIncome != null &&
            isRegular != null &&
            type != null
        ) {
            firestore.saveBudget(
                amount = amount,
                isIncome = isIncome,
                isRegular = isRegular,
                type = type
            )
            Log.d("result","useCase ok")
        }

    }
}