package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.sign_out

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import javax.inject.Inject

class SignOutImpl @Inject constructor(
    private val auth: FirebaseAuthenticator
): SignOut {

    override suspend operator fun invoke() {
        auth.signOut()
    }
}