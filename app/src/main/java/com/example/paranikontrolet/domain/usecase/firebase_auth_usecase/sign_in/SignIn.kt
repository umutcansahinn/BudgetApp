package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.sign_in

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface SignIn {
    suspend operator fun invoke(email: String,password: String): Task<AuthResult>
}