package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val auth: FirebaseAuthenticator
) {
    suspend operator fun invoke(email: String,password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email = email, password = password)
    }
}