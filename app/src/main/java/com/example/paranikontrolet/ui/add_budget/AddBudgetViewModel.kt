package com.example.paranikontrolet.ui.add_budget

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

    fun addBudget(amount: Float?,isIncome: Boolean?,isRegular: Boolean?,type: String?,date: Date?) {
        viewModelScope.launch {
            val currentUser = authUseCase.getCurrentUserInfoUseCase()
            firestoreUseCase.saveBudgetUseCase(
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