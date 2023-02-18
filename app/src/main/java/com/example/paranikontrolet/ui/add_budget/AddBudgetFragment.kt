package com.example.paranikontrolet.ui.add_budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentAddBudgetBinding
import com.example.paranikontrolet.utils.BaseFragment
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.enums.Type
import com.example.paranikontrolet.utils.toFormat
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddBudgetFragment : BaseFragment() {

    private var _binding: FragmentAddBudgetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddBudgetViewModel by viewModels()

    private var amount: Float? = null
    private var isIncome: Boolean? = null
    private var type: String? = null
    private var selectedDate: Date? = null

    private var isHomePage: Boolean = true
    private var argsType: String? = null
    private var argsAmount: String? = null
    private var argsIsIncome: String? = null
    private var argsDate: String? = null
    private var argsDocumentId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBudgetBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            isHomePage = AddBudgetFragmentArgs.fromBundle(it).isHomePage
            argsType = AddBudgetFragmentArgs.fromBundle(it).type
            argsAmount = AddBudgetFragmentArgs.fromBundle(it).amount
            argsIsIncome = AddBudgetFragmentArgs.fromBundle(it).isIncome
            argsDate = AddBudgetFragmentArgs.fromBundle(it).date
            argsDocumentId = AddBudgetFragmentArgs.fromBundle(it).documentId
        }
        initViews()
        observeEvents()
    }

    private fun observeEvents() {

        viewModel.addBudget.observe(viewLifecycleOwner) {
            when (it) {
                is BudgetState.OnSuccess -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }
                is BudgetState.OnFailure -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.deleteBudget.observe(viewLifecycleOwner) {
            when (it) {
                is BudgetState.OnSuccess -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }
                is BudgetState.OnFailure -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.updateBudget.observe(viewLifecycleOwner) {
            when (it) {
                is BudgetState.OnSuccess -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }
                is BudgetState.OnFailure -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun initViews() {
        if (!isHomePage) {
            deleteOrUpdateBudget()
        } else {
            addBudget()
        }

        bottomNavigationViewVisibility = View.GONE
        toolbarVisibility = true
    }

    private fun addBudget() {

        chipItems()
        switchItems()
        calendarItem()

        binding.buttonSave.setOnClickListener {
            amount = binding.editTextAmount.text.toString().toFloatOrNull()
            isIncome = binding.switchIncome.isChecked
            viewModel.addBudget(
                amount = amount,
                isIncome = isIncome,
                type = type,
                date = selectedDate
            )
        }
    }

    private fun deleteOrUpdateBudget() {
        when (argsType) {
            Type.INCOME.type -> binding.chipIncome.isChecked = true
            Type.RENT.type -> binding.chipRent.isChecked = true
            Type.CAR.type -> binding.chipCar.isChecked = true
            Type.ELECTRIC.type -> binding.chipElectric.isChecked = true
            Type.WATER.type -> binding.chipWater.isChecked = true
            Type.GAS.type -> binding.chipFire.isChecked = true
            Type.INTERNET.type -> binding.chipInternet.isChecked = true
            Type.PHONE.type -> binding.chipPhone.isChecked = true
            Type.MARKET.type -> binding.chipMarket.isChecked = true
            Type.CLOTHES.type -> binding.chipClothes.isChecked = true
            Type.EDUCATION.type -> binding.chipEducation.isChecked = true
            else -> binding.chipOthers.isChecked = true
        }

        binding.editTextAmount.setText(argsAmount)
        binding.switchIncome.isChecked = argsIsIncome.toBoolean()
        binding.switchExpense.isChecked = !argsIsIncome.toBoolean()
        binding.buttonCalender.text = argsDate
        binding.buttonSave.visibility = View.GONE
        binding.buttonDelete.visibility = View.VISIBLE
        binding.buttonUpdate.visibility = View.VISIBLE

        binding.buttonDelete.setOnClickListener {
            viewModel.deleteBudget(documentId = argsDocumentId!!)
        }


        chipItems()
        switchItems()
        calendarItem()

        binding.buttonUpdate.setOnClickListener {
            viewModel.updateBudget(
                amount = binding.editTextAmount.text.toString().toFloat(),
                isIncome = binding.switchIncome.isChecked,
                type = type,
                date = selectedDate,
                documentId = argsDocumentId
            )
        }
    }

    private fun chipItems() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chipIncome -> type = binding.chipIncome.text.toString()
                R.id.chipRent -> type = binding.chipRent.text.toString()
                R.id.chipCar -> type = binding.chipCar.text.toString()
                R.id.chipElectric -> type = binding.chipElectric.text.toString()
                R.id.chipWater -> type = binding.chipWater.text.toString()
                R.id.chipFire -> type = binding.chipFire.text.toString()
                R.id.chipInternet -> type = binding.chipInternet.text.toString()
                R.id.chipPhone -> type = binding.chipPhone.text.toString()
                R.id.chipMarket -> type = binding.chipMarket.text.toString()
                R.id.chipClothes -> type = binding.chipClothes.text.toString()
                R.id.chipEducation -> type = binding.chipEducation.text.toString()
                R.id.chipOthers -> type = binding.chipOthers.text.toString()
            }
        }
    }

    private fun switchItems() {
        binding.switchIncome.setOnClickListener {
            binding.switchExpense.isChecked = !binding.switchExpense.isChecked
        }
        binding.switchExpense.setOnClickListener {
            binding.switchIncome.isChecked = !binding.switchIncome.isChecked
        }
    }

    private fun calendarItem() {

        binding.buttonCalender.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(getString(R.string.select_date_button))
                    .setSelection(selectedDate?.time)
                    .build()
            datePicker.addOnPositiveButtonClickListener { timestamp ->
                val selectedUtc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                selectedUtc.timeInMillis = timestamp
                val selectedLocal = Calendar.getInstance()
                selectedLocal.clear()
                selectedLocal.set(
                    selectedUtc.get(Calendar.YEAR),
                    selectedUtc.get(Calendar.MONTH),
                    selectedUtc.get(Calendar.DATE)
                )
                selectedDate = selectedLocal.time
                binding.buttonCalender.text =
                    selectedLocal.time.toFormat(Constants.CURRENT_DATE_FORMAT)
            }
            datePicker.show(parentFragmentManager, Constants.TAG_DATE_PICKER)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}