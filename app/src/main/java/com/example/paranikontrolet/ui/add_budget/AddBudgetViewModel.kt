package com.example.paranikontrolet.ui.add_budget

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.AuthUseCase
import com.example.paranikontrolet.domain.usecase.FirestoreUseCase
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.UpdateBudget
import com.example.paranikontrolet.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddBudgetViewModel @Inject constructor(
    private val firestoreUseCase: FirestoreUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _deleteBudget = MutableLiveData<DeleteBudgetState>()
    val deleteBudget: LiveData<DeleteBudgetState> = _deleteBudget

    private val _updateBudget = MutableLiveData<UpdateBudgetState>()
    val updateBudget: LiveData<UpdateBudgetState> = _updateBudget

    fun addBudget(amount: Float?, isIncome: Boolean?, type: String?, date: Date?) {
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

    fun updateBudget(
        amount: Float?,
        isIncome: Boolean?,
        type: String?,
        date: Date?,
        documentId: String?
    ) {
        viewModelScope.launch {
            val currentUser = authUseCase.getCurrentUserInfo()
            if (
                amount != null &&
                isIncome != null &&
                type != null &&
                date != null &&
                documentId != null &&
                currentUser != null
            ) {
                val hashMap = hashMapOf<String, Any>(
                    Constants.AMOUNT to amount,
                    Constants.IS_INCOME to isIncome,
                    Constants.TYPE to type,
                    Constants.DATE to date.time,
                    Constants.USER_ID to currentUser.uid
                )
                firestoreUseCase.updateBudget(hashMap = hashMap, documentId = documentId)
                    .addOnSuccessListener {
                        _updateBudget.value = UpdateBudgetState.OnSuccess("Data Updated")
                        Log.d("umut", "data updated")
                    }.addOnFailureListener {
                        _updateBudget.value = UpdateBudgetState.OnFailure(it.message.toString())
                        Log.d("umut", it.message.toString())
                    }
            } else {
                _updateBudget.value = UpdateBudgetState.NullData("Please check your data!!")
            }
        }
    }
}

sealed class DeleteBudgetState {
    data class OnSuccess(val message: String) : DeleteBudgetState()
    data class OnFailure(val message: String) : DeleteBudgetState()
}

sealed class UpdateBudgetState {
    data class OnSuccess(val message: String) : UpdateBudgetState()
    data class OnFailure(val message: String) : UpdateBudgetState()
    data class NullData(val message: String) : UpdateBudgetState()
}