package com.example.paranikontrolet.domain.usecase.firestore_database

import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.Resource
import com.google.firebase.Timestamp
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetBudgetFromFirestoreUseCase @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {

    suspend operator fun invoke(): Resource<List<Budget>> {
        val budgetList = ArrayList<Budget>()

        return try {
            Resource.Loading(data = null)

            firestore.getBudgetDocuments().addOnSuccessListener {
                budgetList.clear()
                it.forEach {
                    val budget = Budget(
                        amount = it.get(Constants.AMOUNT).toString().toFloat(),
                        isIncome = true,
                        isRegular = true,
                        type = it.get(Constants.TYPE).toString(),
                        date = Timestamp.now().toDate()
                    )
                    budgetList.add(budget)
                }
            }.await()
            firestore.getBudgetDocuments().addOnFailureListener {
                Resource.Error(it.message.toString(), data = null)
            }.await()
            Resource.Success(budgetList)

        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}
