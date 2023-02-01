package com.example.paranikontrolet.domain.usecase.firebase_auth_usecase

import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.example.paranikontrolet.utils.Resource
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val auth: FirebaseAuthenticator
) {
    suspend operator fun invoke(
        email: String?,
        password: String?
    ): Resource<AuthResult> {
        return if (
            !email.isNullOrBlank() &&
            !password.isNullOrBlank()
        ) {
            try {
                Resource.Loading(data = null)
                val result = auth.signInWithEmailAndPassword(email, password)
                Resource.Success(result)
            } catch (e: Exception) {
                Resource.Error(e.message.toString())
            }
        } else {
            Resource.Error("verileri kontrol ediniz")
        }
    }
}