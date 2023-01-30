package com.example.paranikontrolet.ui.add_budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentAddBudgetBinding
import com.example.paranikontrolet.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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
                type = type
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

        bottomNavigationViewVisibility = View.VISIBLE
        toolbarVisibility = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}