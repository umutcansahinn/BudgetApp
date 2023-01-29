package com.example.paranikontrolet.ui.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.SignInUseCase
import com.example.paranikontrolet.utils.Resource
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _authResult = MutableLiveData<Resource<AuthResult>>()
    val authResult: LiveData<Resource<AuthResult>> = _authResult

    fun signInWithEmailAndPassword(email: String?, password: String?) {
        viewModelScope.launch() {
            val result = signInUseCase(email = email, password = password)
            _authResult.value = result
        }
    }
}
