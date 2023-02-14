package com.example.paranikontrolet.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.UseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _isCurrentUserExist = MutableLiveData<FirebaseUser?>()
    val isCurrentUserExist: LiveData<FirebaseUser?> = _isCurrentUserExist

    init {
        getCurrent()
    }
    fun getCurrent() {
        viewModelScope.launch(Dispatchers.Main) {
            delay(2000)
            _isCurrentUserExist.value = useCases.getCurrentUserInfo()
        }
    }
}