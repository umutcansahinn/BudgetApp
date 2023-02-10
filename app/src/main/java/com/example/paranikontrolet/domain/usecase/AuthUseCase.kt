package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.*

data class AuthUseCase(
    val getCurrentUser: GetCurrentUser,
    val signIn: SignIn,
    val signOut: SignOut,
    val signUp: SignUp,
    val getCurrentUserInfo: GetCurrentUserInfo,
    val forgotPassword: ForgotPassword
)
