package com.example.paranikontrolet.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.domain.usecase.FirebaseAuthUseCases
import com.example.paranikontrolet.domain.usecase.FirebaseFirestoreUseCases
import com.example.paranikontrolet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
private val firebaseFirestoreUseCases: FirebaseFirestoreUseCases,
private val firebaseAuthUseCases: FirebaseAuthUseCases
) : ViewModel() {

    private val _result = MutableLiveData<Resource<List<Budget>>?>()
    val result: LiveData<Resource<List<Budget>>?> = _result


    fun getBudgetFromFirestore() {
        viewModelScope.launch {
            val userId = firebaseAuthUseCases.getCurrentUserInfoUseCase()
            _result.value = firebaseFirestoreUseCases.getBudgetFromFirestoreUseCase(userId!!.uid)
        }
    }
}