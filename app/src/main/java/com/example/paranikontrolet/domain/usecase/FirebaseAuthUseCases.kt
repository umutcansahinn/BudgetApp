package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.GetCurrentUserUseCase
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.SignInUseCase
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.SignOutUseCase
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.SignUpUseCase

data class FirebaseAuthUseCases(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val signInUseCase: SignInUseCase,
    val signOutUseCase: SignOutUseCase,
    val signUpUseCase: SignUpUseCase
)
