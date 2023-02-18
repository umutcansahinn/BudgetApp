package com.example.paranikontrolet.ui.sign_in

import com.google.firebase.auth.AuthResult

sealed class SignInState {
    data class VerificationIsSuccess(val result: AuthResult): SignInState()
    data class VerificationIsFailure(val value: String): SignInState()
    data class SignInIsFailure(val value: String): SignInState()
    data class Loading(val visibility: Boolean): SignInState()
}