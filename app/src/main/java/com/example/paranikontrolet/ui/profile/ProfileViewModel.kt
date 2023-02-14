package com.example.paranikontrolet.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    fun signOut() {
        viewModelScope.launch {
            useCases.signOut()
        }
    }
}