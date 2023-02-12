package com.example.paranikontrolet.ui.add_budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.AuthUseCase
import com.example.paranikontrolet.domain.usecase.FirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddBudgetViewModel @Inject constructor(
    private val firestoreUseCase: FirestoreUseCase,
    private val authUseCase: AuthUseCase
): ViewModel() {

    private val _deleteBudget = MutableLiveData<DeleteBudgetState>()
    val deleteBudget: LiveData<DeleteBudgetState> = _deleteBudget

    fun addBudget(amount: Float?,isIncome: Boolean?,type: String?,date: Date?) {
        viewModelScope.launch {
            val currentUser = authUseCase.getCurrentUserInfo()
            firestoreUseCase.saveBudget(
                amount = amount,
                isIncome = isIncome,
                type = type,
                date = date,
                currentUserId = currentUser?.uid
            )
        }
    }

    fun deleteBudget(documentId: String) {
        viewModelScope.launch {
            firestoreUseCase.deleteBudget(documentId = documentId).addOnSuccessListener {
                _deleteBudget.value = DeleteBudgetState.OnSuccess("Data Deleted!")
            }.addOnFailureListener {
                _deleteBudget.value = DeleteBudgetState.OnFailure(it.message.toString())
            }
        }
    }
}


sealed class DeleteBudgetState {
    data class OnSuccess(val message: String): DeleteBudgetState()
    data class OnFailure(val message: String): DeleteBudgetState()
}