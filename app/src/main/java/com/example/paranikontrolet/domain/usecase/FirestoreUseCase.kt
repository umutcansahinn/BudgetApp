package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.*

data class FirestoreUseCase(
    val getBudgetFromFirestore: GetBudgetFromFirestore,
    val saveBudget: SaveBudget,
    val saveUser: SaveUser,
    val deleteBudget: DeleteBudget,
    val updateBudget: UpdateBudget
)