package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.DeleteBudget
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.GetBudgetFromFirestore
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.SaveBudget
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.SaveUser

data class FirestoreUseCase(
    val getBudgetFromFirestore: GetBudgetFromFirestore,
    val saveBudget: SaveBudget,
    val saveUser: SaveUser,
    val deleteBudget: DeleteBudget
)