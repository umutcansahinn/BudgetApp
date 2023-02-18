package com.example.paranikontrolet.domain.mapper

import android.graphics.Color
import com.example.paranikontrolet.R
import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.utils.enums.Type
import com.example.paranikontrolet.utils.enums.UiColor

class BudgetMapper {

    fun map(entity: Budget): BudgetUiModel {
        return entity.toUiModel()
    }

    private fun Budget.toUiModel() = BudgetUiModel(
        amount = getAmount(),
        isIncome = getIncome(),
        type = getType(),
        date = getDate(),
        icon = getIcon(),
        textColor = getTextColor(),
        strokeColor = getStrokeColor(),
        chartColor = getChartColor(),
        cardColor = getCardColor(),
        id = id
    )

    private fun Budget.getAmount() = amount
    private fun Budget.getIncome() = isIncome
    private fun Budget.getType() = type
    private fun Budget.getDate() = date
    private fun Budget.getIcon() = when (type) {
        Type.INCOME.type -> R.drawable.income_icon
        Type.RENT.type -> R.drawable.home_icon
        Type.CAR.type -> R.drawable.car_icon
        Type.ELECTRIC.type -> R.drawable.lightbulb_icon
        Type.WATER.type -> R.drawable.water_icon
        Type.GAS.type -> R.drawable.fire_icon
        Type.INTERNET.type -> R.drawable.internet_icon
        Type.PHONE.type -> R.drawable.phone_icon
        Type.MARKET.type -> R.drawable.market_icon
        Type.CLOTHES.type -> R.drawable.clothes_icon
        Type.EDUCATION.type -> R.drawable.education_icon
        else -> R.drawable.others_icon
    }

    private fun Budget.getTextColor() = when (isIncome) {
        true -> Color.GREEN
        else -> Color.RED
    }

    private fun Budget.getStrokeColor() = when (isIncome) {
        true -> Color.GREEN
        else -> Color.RED
    }

    private fun Budget.getChartColor() = when (type) {
        Type.INCOME.type -> UiColor.Green.color
        else -> UiColor.Red.color
    }

    private fun Budget.getCardColor() = when (isIncome) {
        true -> UiColor.Green.color
        else -> UiColor.Red.color
    }
}
