package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.get_current_user_info

import com.google.firebase.auth.FirebaseUser

interface GetCurrentUserInfo {
    suspend operator fun invoke(): FirebaseUser?
}