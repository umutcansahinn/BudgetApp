package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase

import android.icu.text.DateFormat
import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.Resource
import com.google.firebase.Timestamp
import java.util.Date
import javax.inject.Inject
import kotlin.collections.ArrayList

class GetBudgetFromFirestoreUseCase @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {
    suspend operator fun invoke(userId: String): Resource<List<Budget>> {
        return try {
            Resource.Loading(data = null)
            val result = firestore.getBudgetDocuments(userId = userId)
            val budgetList = ArrayList<Budget>()
            budgetList.clear()
            result.forEach {
                val budget = Budget(
                    amount = it.get(Constants.AMOUNT).toString().toFloat(),
                    isIncome = it.get(Constants.IS_INCOME).toString().toBoolean(),
                    isRegular = it.get(Constants.IS_REGULAR).toString().toBoolean(),
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

//java.sql.Date.valueOf(it.get(Constants.DATE).toString())