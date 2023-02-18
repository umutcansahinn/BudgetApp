package com.example.paranikontrolet.ui.forgot_password

sealed class ForgotPasswordState {
    data class PasswordSuccess(val message: String) : ForgotPasswordState()
    data class PasswordFailure(val message: String) : ForgotPasswordState()
}
