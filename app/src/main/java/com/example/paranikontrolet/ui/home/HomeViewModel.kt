package com.example.paranikontrolet.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.domain.usecase.AuthUseCase
import com.example.paranikontrolet.domain.usecase.FirestoreUseCase
import com.example.paranikontrolet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firestoreUseCase: FirestoreUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _result = MutableLiveData<Resource<List<BudgetUiModel>>>()
    val result: LiveData<Resource<List<BudgetUiModel>>> = _result


    fun getBudgetFromFirestore() {
        viewModelScope.launch {
            val userId = authUseCase.getCurrentUserInfoUseCase()
            _result.value = firestoreUseCase.getBudgetFromFirestoreUseCase(userId!!.uid)
        }
    }
}