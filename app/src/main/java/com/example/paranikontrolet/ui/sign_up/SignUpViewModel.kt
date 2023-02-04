package com.example.paranikontrolet.ui.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.AuthUseCase
import com.example.paranikontrolet.domain.usecase.FirestoreUseCase
import com.example.paranikontrolet.utils.Resource
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val firestoreUseCase: FirestoreUseCase
) : ViewModel() {


    private val _authResult = MutableLiveData<Resource<AuthResult>>()
    val authResult: LiveData<Resource<AuthResult>> = _authResult

    fun signInWithEmailAndPassword(
        email: String?,
        name: String?,
        password: String?,
        verifyPassword: String?
    ) {
        viewModelScope.launch() {
            val result = authUseCase.signUpUseCase(
                email = email,
                name = name,
                password = password,
                verifyPassword = verifyPassword
            )
            _authResult.value = result
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