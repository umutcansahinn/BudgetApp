package com.example.paranikontrolet.domain.mapper

import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel

class BudgetMapper {

    fun map(entity: Budget): BudgetUiModel {
        return entity.toUiModel()
    }

    private fun Budget.toUiModel() = BudgetUiModel(
        amount = getAmount(),
        isIncome = getIncome(),
        isRegular = getIsRegular(),
        type = getType(),
        date = getDate()
    )

    private fun Budget.getAmount() = amount
    private fun Budget.getIncome() = isIncome
    private fun Budget.getIsRegular() = isRegular
    private fun Budget.getType() = type
    private fun Budget.getDate() = date
}