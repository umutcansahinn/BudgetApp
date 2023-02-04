package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.*

data class AuthUseCase(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val signInUseCase: SignInUseCase,
    val signOutUseCase: SignOutUseCase,
    val signUpUseCase: SignUpUseCase,
    val getCurrentUserInfoUseCase: GetCurrentUserInfoUseCase
)
