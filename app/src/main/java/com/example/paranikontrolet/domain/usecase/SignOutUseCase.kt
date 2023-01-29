package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val auth: FirebaseAuthenticator
) {

    suspend operator fun invoke() {
        auth.signOut()
    }
}