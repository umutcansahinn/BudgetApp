package com.example.paranikontrolet.ui.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.AuthUseCase
import com.example.paranikontrolet.domain.usecase.FirestoreUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val firestoreUseCase: FirestoreUseCase
) : ViewModel() {


    private val _authResult = MutableLiveData<SignUpUiState>()
    val authResult: LiveData<SignUpUiState> = _authResult


   fun signInWithEmailAndPassword(
        email: String?,
        name: String?,
        password: String?,
        verifyPassword: String?
    ) {
       if (
           !email.isNullOrBlank() &&
           !name.isNullOrBlank() &&
           !password.isNullOrBlank() &&
           !verifyPassword.isNullOrBlank() &&
           password == verifyPassword
       ) {

           viewModelScope.launch {
               _authResult.value = SignUpUiState.Loading(true)
               authUseCase.signUpUseCase(email,password).addOnCompleteListener {task ->
                   if (task.isSuccessful) {
                       viewModelScope.launch {
                        authUseCase.getCurrentUserInfoUseCase()?.sendEmailVerification()
                            ?.addOnSuccessListener {
                                _authResult.value = SignUpUiState
                                    .SendEmailIsSuccess("Please check your e-mail")
                            }?.addOnFailureListener {
                                _authResult.value = SignUpUiState.SendEmailIsFailure("Please try again later")
                            }
                       }
                       _authResult.value = SignUpUiState.Loading(false)
                       _authResult.value = SignUpUiState.SignUpIsSuccess(task.result)
                   }
               }.addOnFailureListener {
                   _authResult.value = SignUpUiState.SignUpIsFailure(it.message.toString())
               }
           }
       }
    }

    fun saveUser(
        getFirebaseUserUid: String,
        email: String,
        name: String,
        password: String
    ) {
        viewModelScope.launch {
            firestoreUseCase.saveUserUseCase(
                getFirebaseUserUid = getFirebaseUserUid,
                email = email,
                name = name,
                password = password
            )
        }

    }
}

sealed class SignUpUiState {
    data class SendEmailIsSuccess(val value: String): SignUpUiState()
    data class SendEmailIsFailure(val value: String): SignUpUiState()
    data class SignUpIsSuccess(val result: AuthResult): SignUpUiState()
    data class SignUpIsFailure(val value: String): SignUpUiState()
    data class Loading(val visibility: Boolean): SignUpUiState()
}