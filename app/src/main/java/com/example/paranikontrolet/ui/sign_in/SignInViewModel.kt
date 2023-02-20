package com.example.paranikontrolet.ui.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _authResult = MutableLiveData<SignInState>()
    val authResult: LiveData<SignInState> = _authResult


    fun signInWithEmailAndPassword(email: String?, password: String?) {
        if (
            !email.isNullOrBlank() &&
            !password.isNullOrBlank()
        ) {
            viewModelScope.launch {
                _authResult.value = SignInState.Loading(true)
                useCases.signIn(email,password).addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        viewModelScope.launch{
                           val verification =  useCases.getCurrentUserInfo()?.isEmailVerified
                            verification?.let {
                                if (it) {
                                    _authResult.value = SignInState.Loading(false)
                                    _authResult.value = SignInState.VerificationIsSuccess(task.result)
                                }else {
                                    _authResult.value = SignInState.VerificationIsFailure("Please check your e-mail")
                                }
                            }
                        }
                    }
                }.addOnFailureListener {
                    _authResult.value = SignInState.SignInIsFailure(it.message.toString())
                }
            }

        }
    }

}
