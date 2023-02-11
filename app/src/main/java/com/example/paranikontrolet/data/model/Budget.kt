package com.example.paranikontrolet.data.model

import java.util.*

data class Budget(
    val id: String? = null,
    val amount: Float,
    val isIncome: Boolean,
    val type: String,
    val date: Date
)