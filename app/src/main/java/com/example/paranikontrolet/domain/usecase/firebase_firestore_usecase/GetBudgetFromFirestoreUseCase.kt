package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase

import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.Resource
import com.google.firebase.Timestamp
import javax.inject.Inject

class GetBudgetFromFirestoreUseCase @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {
    suspend operator fun invoke(): Resource<List<Budget>> {
        return try {
            Resource.Loading(data = null)
            val result = firestore.getBudgetDocuments()
            val budgetList = ArrayList<Budget>()
            budgetList.clear()
            result.forEach {
                val budget = Budget(
                    amount = it.get(Constants.AMOUNT).toString().toFloat(),
                    isIncome = true,
                    isRegular = true,
                    type = it.get(Constants.TYPE).toString(),
                    date = Timestamp.now().toDate()
                )
                budgetList.add(budget)
            }
            Resource.Success(budgetList)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}
