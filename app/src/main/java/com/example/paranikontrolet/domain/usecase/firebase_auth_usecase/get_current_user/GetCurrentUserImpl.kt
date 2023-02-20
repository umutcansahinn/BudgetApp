package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.get_current_user

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import javax.inject.Inject

class GetCurrentUserImpl @Inject constructor(
    private val auth: FirebaseAuthenticator
) : GetCurrentUser {

    override suspend operator fun invoke(): Boolean {
        return auth.getCurrentUser()
    }
}