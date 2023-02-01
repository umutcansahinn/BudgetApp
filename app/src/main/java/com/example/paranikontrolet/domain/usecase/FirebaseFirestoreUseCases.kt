package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.GetBudgetFromFirestoreUseCase
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.SaveBudgetUseCase
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.SaveUserUseCase

data class FirebaseFirestoreUseCases(
    val getBudgetFromFirestoreUseCase: GetBudgetFromFirestoreUseCase,
    val saveBudgetUseCase: SaveBudgetUseCase,
    val saveUserUseCase: SaveUserUseCase
)