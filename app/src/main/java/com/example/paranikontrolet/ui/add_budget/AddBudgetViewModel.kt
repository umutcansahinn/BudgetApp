package com.example.paranikontrolet.ui.add_budget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.FirebaseAuthUseCases
import com.example.paranikontrolet.domain.usecase.FirebaseFirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddBudgetViewModel @Inject constructor(
private val firebaseFirestoreUseCases: FirebaseFirestoreUseCases,
private val firebaseAuthUseCases: FirebaseAuthUseCases
): ViewModel() {

    fun addBudget(amount: Float?,isIncome: Boolean?,isRegular: Boolean?,type: String?,date: Date) {
        viewModelScope.launch {
            val currentUser = firebaseAuthUseCases.getCurrentUserInfoUseCase()
            firebaseFirestoreUseCases.saveBudgetUseCase(
                amount = amount,
                isIncome = isIncome,
                isRegular = isRegular,
                type = type,
                date = date,
                currentUserId = currentUser?.uid
            )
        }
    }
}