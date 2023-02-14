package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase

import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.domain.mapper.BudgetMapper
import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.Resource
import java.util.Date
import javax.inject.Inject
import kotlin.collections.ArrayList

class GetBudgetFromFirestore @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase,
    private val auth: FirebaseAuthenticator,
    private val mapper: BudgetMapper
) {

    suspend operator fun invoke(): Resource<List<BudgetUiModel>> {
        val userId = auth.getCurrentUserInfo()!!.uid

        return try {
            Resource.Loading(data = null)
            val result = firestore.getBudgetDocuments(userId = userId)
            val budgetList = ArrayList<BudgetUiModel>()
            budgetList.clear()
            result.forEach {
                val budget = Budget(
                    amount = it.get(Constants.AMOUNT).toString().toFloat(),
                    isIncome = it.get(Constants.IS_INCOME).toString().toBoolean(),
                    type = it.get(Constants.TYPE).toString(),
                    date = Date(it.get(Constants.DATE).toString().toLong()),
                    id = it.id
                ).run {
                    mapper.map(this)
                }
                budgetList.add(budget)
            }
            Resource.Success(budgetList)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }


    }
 /*   suspend operator fun invoke(userId: String): Resource<List<BudgetUiModel>> {
        return try {
            Resource.Loading(data = null)
            val result = firestore.getBudgetDocuments(userId = userId)
            val budgetList = ArrayList<BudgetUiModel>()
            budgetList.clear()
            result.forEach {
                val budget = Budget(
                    amount = it.get(Constants.AMOUNT).toString().toFloat(),
                    isIncome = it.get(Constants.IS_INCOME).toString().toBoolean(),
                    type = it.get(Constants.TYPE).toString(),
                    date = Date(it.get(Constants.DATE).toString().toLong()),
                    id = it.id
                ).run {
                    mapper.map(this)
                }
                budgetList.add(budget)
            }
            Resource.Success(budgetList)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }*/
