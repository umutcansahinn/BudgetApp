package com.example.paranikontrolet.ui.sign_up

import com.google.firebase.auth.AuthResult


sealed class SignUpState {
    data class SendEmailIsSuccess(val value: String): SignUpState()
    data class SendEmailIsFailure(val value: String): SignUpState()
    data class SignUpIsSuccess(val result: AuthResult): SignUpState()
    data class SignUpIsFailure(val value: String): SignUpState()
    data class Loading(val visibility: Boolean): SignUpState()
}