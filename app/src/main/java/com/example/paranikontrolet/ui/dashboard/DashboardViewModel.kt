package com.example.paranikontrolet.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.FirebaseAuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val firebaseAuthUseCases: FirebaseAuthUseCases
) : ViewModel() {

    fun signOut() {
        viewModelScope.launch {
            firebaseAuthUseCases.signOutUseCase()
        }
    }
}