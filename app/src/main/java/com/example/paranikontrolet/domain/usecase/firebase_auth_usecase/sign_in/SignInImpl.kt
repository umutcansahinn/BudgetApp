package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.sign_in

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignInImpl @Inject constructor(
    private val auth: FirebaseAuthenticator
) : SignIn {
    override suspend operator fun invoke(
        email: String,
        password: String
    ): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email = email, password = password)
    }

}