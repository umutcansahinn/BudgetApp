package com.example.paranikontrolet.ui.add_budget

sealed class BudgetState {
    data class OnSuccess(val message: String) : BudgetState()
    data class OnFailure(val message: String) : BudgetState()
}
