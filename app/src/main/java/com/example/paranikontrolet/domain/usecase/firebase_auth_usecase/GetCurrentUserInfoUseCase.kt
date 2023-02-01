package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUserInfoUseCase @Inject constructor(
    private val auth: FirebaseAuthenticator
) {

    suspend operator fun invoke(): FirebaseUser? {
        return auth.getCurrentUserInfo()
    }
}