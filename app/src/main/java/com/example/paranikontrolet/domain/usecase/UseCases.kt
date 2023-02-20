package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.forgot_password.ForgotPasswordImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.get_current_user.GetCurrentUserImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.get_current_user_info.GetCurrentUserInfoImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.sign_in.SignInImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.sign_out.SignOutImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.sign_up.SignUpImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.delete_budget.DeleteBudgetImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.get_budget_from_firestore.GetBudgetFromFirestoreImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.save_budget.SaveBudgetImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.save_user.SaveUserImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.update_budget.UpdateBudgetImpl

data class UseCases(
    val getCurrentUser: GetCurrentUserImpl,
    val signIn: SignInImpl,
    val signOut: SignOutImpl,
    val signUp: SignUpImpl,
    val getCurrentUserInfo: GetCurrentUserInfoImpl,
    val forgotPassword: ForgotPasswordImpl,

    val getBudgetFromFirestore: GetBudgetFromFirestoreImpl,
    val saveBudget: SaveBudgetImpl,
    val saveUser: SaveUserImpl,
    val deleteBudget: DeleteBudgetImpl,
    val updateBudget: UpdateBudgetImpl
)
