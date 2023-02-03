package com.example.paranikontrolet.domain.ui_model

import java.util.*

data class BudgetUiModel(
    val amount: Float,
    val isIncome: Boolean,
    val isRegular: Boolean,
    val type: String,
    val date: Date
)
