package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.get_current_user_info

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUserInfoImpl @Inject constructor(
    private val auth: FirebaseAuthenticator
): GetCurrentUserInfo {

    override suspend operator fun invoke(): FirebaseUser? {
        return auth.getCurrentUserInfo()
    }
}