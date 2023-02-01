package com.example.paranikontrolet.ui.add_budget

import android.os.BugreportManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.paranikontrolet.R
import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.databinding.FragmentAddBudgetBinding
import com.example.paranikontrolet.ui.base.BaseFragment
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.toFormat
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddBudgetFragment : BaseFragment() {

    private var _binding: FragmentAddBudgetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddBudgetViewModel by viewModels()

    var amount: Float? = null
    var isIncome: Boolean? = null
    var isRegular: Boolean? = null
    var type: String? = null
    //data ekle

    private var selectedDate = Date()

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

        observeEvent()
        initViews()

    }

    private fun observeEvent() {

    }

    private fun initViews() {

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chipIncome -> type = binding.chipIncome.text.toString()
                R.id.chipRent -> type = binding.chipRent.text.toString()
                R.id.chipInvoices -> type = binding.chipInvoices.text.toString()
                R.id.chipFuel -> type = binding.chipFuel.text.toString()
                R.id.chipAnother -> type = binding.chipAnother.text.toString()
                R.id.chipBill -> type = binding.chipBill.text.toString()
                R.id.chipPhone -> type = binding.chipPhone.text.toString()
            }
        }

        binding.buttonSave.setOnClickListener {

            amount = binding.editTextAmount.text.toString().toFloatOrNull()
            isIncome = binding.switchIncome.isChecked
            isRegular = binding.switchRegular.isChecked



            viewModel.addBudget(
                amount = amount,
                isIncome = isIncome,
                isRegular = isRegular,
                type = type,
                date = selectedDate
            )
            findNavController().popBackStack()
        }


        binding.switchIncome.setOnClickListener {
            binding.switchExpense.isChecked = !binding.switchExpense.isChecked
        }
        binding.switchExpense.setOnClickListener {
            binding.switchIncome.isChecked = !binding.switchIncome.isChecked
        }
        binding.switchRegular.setOnClickListener {
            binding.switchNotRegular.isChecked = !binding.switchNotRegular.isChecked
        }
        binding.switchNotRegular.setOnClickListener {
            binding.switchRegular.isChecked = !binding.switchRegular.isChecked
        }

        binding.buttonCalender.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(getString(R.string.select_date_button))
                    .setSelection(selectedDate.time)
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

        bottomNavigationViewVisibility = View.VISIBLE
        toolbarVisibility = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}