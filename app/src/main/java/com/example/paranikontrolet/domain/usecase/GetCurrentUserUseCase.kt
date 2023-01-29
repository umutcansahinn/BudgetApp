package com.example.paranikontrolet.domain.usecase

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val auth: FirebaseAuthenticator
) {

    suspend operator fun invoke(): Boolean{
        return auth.getCurrentUser()
    }
}