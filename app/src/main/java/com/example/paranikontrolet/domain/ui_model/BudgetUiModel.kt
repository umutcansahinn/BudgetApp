package com.example.paranikontrolet.domain.ui_model

import java.util.*

data class BudgetUiModel(
    val amount: Float,
    val isIncome: Boolean,
    val isRegular: Boolean,
    val type: String,
    val date: Date,
    val icon: Int,
    val textColor: Int,
    val strokeColor: Int,
    val chartColor: String,
    val cardColor: String

)
