package com.example.paranikontrolet.data.model

import java.util.*

data class Budget(
    val amount: Int,
    val isIncome: Boolean,
    val isRegular: Boolean,
    val type: String,
    val date: Date
)