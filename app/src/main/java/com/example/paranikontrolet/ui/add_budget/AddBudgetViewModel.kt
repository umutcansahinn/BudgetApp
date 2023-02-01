package com.example.paranikontrolet.ui.add_budget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.usecase.FirebaseFirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBudgetViewModel @Inject constructor(
private val firebaseFirestoreUseCases: FirebaseFirestoreUseCases
): ViewModel() {

    fun addBudget(amount: Float?,isIncome: Boolean?,isRegular: Boolean?,type: String?) {
        viewModelScope.launch {
            firebaseFirestoreUseCases.saveBudgetUseCase(
                amount = amount,
                isIncome = isIncome,
                isRegular = isRegular,
                type = type
            )
        }
    }
}