package com.example.paranikontrolet.ui.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.AuthUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _authResult = MutableLiveData<SignInUiState>()
    val authResult: LiveData<SignInUiState> = _authResult


    fun signInWithEmailAndPassword(email: String?, password: String?) {
        if (
            !email.isNullOrBlank() &&
            !password.isNullOrBlank()
        ) {
            viewModelScope.launch {
                _authResult.value = SignInUiState.Loading(true)
                authUseCase.signIn(email,password).addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        viewModelScope.launch{
                           val verification =  authUseCase.getCurrentUserInfo()?.isEmailVerified
                            verification?.let {
                                if (it) {
                                    _authResult.value = SignInUiState.Loading(false)
                                    _authResult.value = SignInUiState.VerificationIsSuccess(task.result)
                                }else {
                                    _authResult.value = SignInUiState.VerificationIsFailure("Please check your e-mail")
                                }
                            }
                        }
                    }
                }.addOnFailureListener {
                    _authResult.value = SignInUiState.SignInIsFailure(it.message.toString())
                }
            }

        }
    }

}
sealed class SignInUiState {
    data class VerificationIsSuccess(val result: AuthResult): SignInUiState()
    data class VerificationIsFailure(val value: String): SignInUiState()
    data class SignInIsFailure(val value: String): SignInUiState()
    data class Loading(val visibility: Boolean): SignInUiState()
}