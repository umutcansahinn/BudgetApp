package com.example.paranikontrolet.ui.forgot_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {


    private val _forgotPassword = MutableLiveData<ForgotPasswordState>()
    val forgotPassword: LiveData<ForgotPasswordState> = _forgotPassword

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            useCases.forgotPassword(email = email).addOnSuccessListener {
                _forgotPassword.value =
                    ForgotPasswordState.PasswordSuccess("Password reset mail sent, please check your mail!")
            }.addOnFailureListener {
                _forgotPassword.value =
                    ForgotPasswordState.PasswordFailure(it.message.toString())
            }
        }
    }
}