package com.example.paranikontrolet.domain.mapper

import android.graphics.Color
import com.example.paranikontrolet.R
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
        date = getDate(),
        icon = getIcon(),
        textColor = getTextColor(),
        strokeColor = getStrokeColor(),
        chartColor = getChartColor(),
        cardColor = getCardColor()
    )

    private fun Budget.getAmount() = amount
    private fun Budget.getIncome() = isIncome
    private fun Budget.getIsRegular() = isRegular
    private fun Budget.getType() = type
    private fun Budget.getDate() = date
    private fun Budget.getIcon() = when(type) {
        "income" -> R.drawable.income_icon
        "rent" -> R.drawable.home_icon
        "car" -> R.drawable.car_icon
        "electric" -> R.drawable.lightbulb_icon
        "water" -> R.drawable.water_icon
        "gas" -> R.drawable.fire_icon
        "internet" -> R.drawable.internet_icon
        "phone" -> R.drawable.phone_icon
        "market" -> R.drawable.market_icon
        "clothes" -> R.drawable.clothes_icon
        "education" -> R.drawable.education_icon
        else -> R.drawable.others_icon
    }

    private fun Budget.getTextColor() = when(isIncome) {
        true-> Color.GREEN
        else-> Color.RED
    }

    private fun Budget.getStrokeColor() = when(isIncome) {
        true -> Color.GREEN
        else -> Color.RED
    }

    private fun Budget.getChartColor() = when(type) {
        "income" -> "#64DD17"
        else -> "#D50000"
    }

    private fun Budget.getCardColor() = when(isIncome) {
        true -> "#63FF00"
        else -> "#FF0000"
    }
}