package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.get_budget_from_firestore

import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.utils.Resource

interface GetBudgetFromFirestore {
    suspend operator fun invoke(): Resource<List<BudgetUiModel>>
}