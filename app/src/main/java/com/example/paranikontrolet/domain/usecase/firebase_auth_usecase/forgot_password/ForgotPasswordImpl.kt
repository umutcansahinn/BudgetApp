package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.forgot_password

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class ForgotPasswordImpl @Inject constructor(
    private val auth: FirebaseAuthenticator
) : ForgotPassword {
    override suspend operator fun invoke(email: String): Task<Void> {
        return auth.forgotPassword(email)
    }
}