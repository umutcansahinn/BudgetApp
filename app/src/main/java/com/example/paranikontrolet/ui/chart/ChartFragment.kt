package com.example.paranikontrolet.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.paranikontrolet.databinding.FragmentChartBinding
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.ui.base.BaseFragment
import com.example.paranikontrolet.utils.Resource
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartFragment : BaseFragment() {

    private var _binding: FragmentChartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChartViewModel by viewModels()

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
                    it.data?.let {
                        setBarChart(it)
                        setPieChart(it)
                    }/*
                    binding.calendarView.visibility = View.VISIBLE
                    binding.textViewError.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE*/
                }
                is Resource.Error -> {/*
                    binding.calendarView.visibility = View.GONE
                    binding.textViewError.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE*/
                }
                is Resource.Loading -> {/*
                    binding.calendarView.visibility = View.GONE
                    binding.textViewError.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE*/
                }
            }
        }
    }

    private fun setBarChart(list: List<BudgetUiModel>) {

    }

    private fun setPieChart(list: List<BudgetUiModel>) {

        var incomeAmount = 0f
        var expenseAmount = 0f
        var incomeColor = "#FFFFFF"
        var expenseColor = "#FFFFFF"



        list.forEach {
            when (it.isIncome) {
                true -> {
                    incomeAmount += it.amount
                    incomeColor = it.chartColor
                }
                else -> {
                    expenseAmount += it.amount
                    expenseColor = it.chartColor
                }
            }

        }
        val pieEntry = mutableListOf<PieEntry>()
        pieEntry.add(PieEntry(incomeAmount, "income", "income"))
        pieEntry.add(PieEntry(expenseAmount, "expense", "expense"))

        val pieDataSet = PieDataSet(pieEntry, "")
        pieDataSet.setColors(
            Color.parseColor(incomeColor),
            Color.parseColor(expenseColor),
        )

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