package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val auth: FirebaseAuthenticator
) {
    suspend operator fun invoke(email: String): Task<Void> {
        return auth.forgotPassword(email)
    }
}