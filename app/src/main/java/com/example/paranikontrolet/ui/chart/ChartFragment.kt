package com.example.paranikontrolet.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentChartBinding
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.utils.BaseFragment
import com.example.paranikontrolet.utils.Resource
import com.example.paranikontrolet.utils.enums.Type
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartFragment : BaseFragment() {

    private var _binding: FragmentChartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChartViewModel by viewModels()

    private var income = 0f
    private var rent = 0f
    private var car = 0f
    private var electric = 0f
    private var water = 0f
    private var gas = 0f
    private var internet = 0f
    private var phone = 0f
    private var market = 0f
    private var clothes = 0f
    private var education = 0f
    private var others = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChartBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBudgetFromFirestore()
        initViews()
        observeEvent()
    }

    private fun initViews() {

        binding.switchType.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.barChart.visibility = View.VISIBLE
                binding.pieChart.visibility = View.GONE
            } else {
                binding.barChart.visibility = View.GONE
                binding.pieChart.visibility = View.VISIBLE
            }
        }


        bottomNavigationViewVisibility = View.VISIBLE
        toolbarVisibility = true
    }

    private fun observeEvent() {
        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    if (it.data != null && it.data.isEmpty().not()) {
                            setBarChart(it.data)
                            setPieChart(it.data)
                        binding.textViewError.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.textViewNullData.visibility = View.GONE
                    } else {
                        binding.showLinearLayout.visibility = View.GONE
                        binding.textViewNullData.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.textViewError.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.barChart.visibility = View.GONE
                    binding.pieChart.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.textViewError.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.barChart.visibility = View.GONE
                    binding.pieChart.visibility = View.GONE
                }
            }
        }
    }

    private fun setBarChart(list: List<BudgetUiModel>) {

        val barEntry = ArrayList<BarEntry>()


        list.forEach {
            when (it.type) {
                Type.INCOME.type -> income += it.amount
                Type.RENT.type -> rent += it.amount
                Type.CAR.type -> car += it.amount
                Type.ELECTRIC.type -> electric += it.amount
                Type.WATER.type -> water += it.amount
                Type.GAS.type -> gas += it.amount
                Type.INTERNET.type -> internet += it.amount
                Type.PHONE.type -> phone += it.amount
                Type.MARKET.type -> market += it.amount
                Type.CLOTHES.type -> clothes += it.amount
                Type.EDUCATION.type -> education += it.amount
                else -> others += it.amount
            }
        }
        barEntry.add(BarEntry(1f, income, R.drawable.income_icon))
        barEntry.add(BarEntry(2f, rent, R.drawable.home_icon))
        barEntry.add(BarEntry(3f, car, R.drawable.car_icon))
        barEntry.add(BarEntry(4f, electric, R.drawable.lightbulb_icon))
        barEntry.add(BarEntry(5f, water, R.drawable.water_icon))
        barEntry.add(BarEntry(6f, gas, R.drawable.fire_icon))
        barEntry.add(BarEntry(7f, internet, R.drawable.internet_icon))
        barEntry.add(BarEntry(8f, phone, R.drawable.phone_icon))
        barEntry.add(BarEntry(9f, market, R.drawable.market_icon))
        barEntry.add(BarEntry(10f, clothes, R.drawable.clothes_icon))
        barEntry.add(BarEntry(11f, education, R.drawable.education_icon))
        barEntry.add(BarEntry(12f, others, R.drawable.others_icon))

        val barDataSet = BarDataSet(barEntry, "list")
        barDataSet.valueTextSize = 15f
        barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
        barDataSet.valueTextColor = Color.BLACK

        val barData = BarData(barDataSet)
        binding.barChart.setFitBars(false)
        binding.barChart.data = barData
        binding.barChart.description.text = "Bar Chart"
        binding.barChart.xAxis.valueFormatter = IndexAxisValueFormatter()
        binding.barChart.xAxis.position = XAxis.XAxisPosition.TOP
        binding.barChart.animateY(2000)
    }

    private fun setPieChart(list: List<BudgetUiModel>) {

        var incomeAmount = 0f
        var expenseAmount = 0f

        list.forEach {
            when (it.isIncome) {
                true -> {
                    incomeAmount += it.amount
                }
                else -> {
                    expenseAmount += it.amount
                }
            }
        }

        val pieEntry = mutableListOf<PieEntry>()
        pieEntry.add(PieEntry(incomeAmount, "income", "income"))
        pieEntry.add(PieEntry(expenseAmount, "expense", "expense"))

        val pieDataSet = PieDataSet(pieEntry, "")
        pieDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

        pieDataSet.valueTextSize = 30f
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.formSize = 30f
        pieDataSet.form = Legend.LegendForm.CIRCLE
        val data = PieData(pieDataSet)
        binding.pieChart.data = data
        binding.pieChart.animateY(2000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}