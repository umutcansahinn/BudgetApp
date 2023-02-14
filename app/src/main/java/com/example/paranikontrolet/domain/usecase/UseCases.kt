package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.*
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.*

data class UseCases(
    val getCurrentUser: GetCurrentUser,
    val signIn: SignIn,
    val signOut: SignOut,
    val signUp: SignUp,
    val getCurrentUserInfo: GetCurrentUserInfo,
    val forgotPassword: ForgotPassword,

    val getBudgetFromFirestore: GetBudgetFromFirestore,
    val saveBudget: SaveBudget,
    val saveUser: SaveUser,
    val deleteBudget: DeleteBudget,
    val updateBudget: UpdateBudget
)
