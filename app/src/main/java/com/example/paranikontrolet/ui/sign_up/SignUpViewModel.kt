package com.example.paranikontrolet.ui.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.UseCases
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {


    private val _authResult = MutableLiveData<SignUpState>()
    val authResult: LiveData<SignUpState> = _authResult


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
               _authResult.value = SignUpState.Loading(true)
               useCases.signUp(email,password).addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       viewModelScope.launch {
                        useCases.getCurrentUserInfo()?.sendEmailVerification()
                            ?.addOnSuccessListener {
                                _authResult.value = SignUpState
                                    .SendEmailIsSuccess("Please check your e-mail")
                            }?.addOnFailureListener {
                                _authResult.value = SignUpState.SendEmailIsFailure("Please try again later")
                            }
                       }
                       _authResult.value = SignUpState.Loading(false)
                       _authResult.value = SignUpState.SignUpIsSuccess(task.result)
                   }
               }.addOnFailureListener {
                   _authResult.value = SignUpState.SignUpIsFailure(it.message.toString())
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
            useCases.saveUser(
                getFirebaseUserUid = getFirebaseUserUid,
                email = email,
                name = name,
                password = password
            )
        }

    }
}
