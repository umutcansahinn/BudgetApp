package com.example.paranikontrolet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paranikontrolet.databinding.FragmentHomeBinding
import com.example.paranikontrolet.utils.BaseFragment
import com.example.paranikontrolet.ui.home.adapter.BudgetListAdapter
import com.example.paranikontrolet.utils.Resource
import com.example.paranikontrolet.utils.gone
import com.example.paranikontrolet.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val budgetListAdapter = BudgetListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBudgetFromFirestore()
        observeEvent()
        initViews()

    }

    private fun observeEvent() {
        viewModel.result.observe(viewLifecycleOwner) { list ->
            when (list) {
                is Resource.Success -> {
                    if (list.data != null && list.data.isEmpty().not()) {
                        val newList = list.data.sortedWith(compareByDescending { it.date })
                        budgetListAdapter.budgetList = newList

                        binding.recyclerView.visible()
                        binding.progressBar.gone()
                        binding.textViewError.gone()
                        binding.textViewNullData.gone()
                    } else {
                        binding.textViewNullData.visible()
                        binding.recyclerView.gone()
                        binding.textViewError.gone()
                        binding.progressBar.gone()
                    }
                }
                is Resource.Error -> {
                    binding.textViewError.text = list.message.toString()

                    binding.textViewError.visible()
                    binding.recyclerView.gone()
                    binding.progressBar.gone()
                    binding.textViewNullData.gone()
                }
                is Resource.Loading -> {
                    binding.textViewError.gone()
                    binding.recyclerView.gone()
                    binding.progressBar.visible()
                    binding.textViewNullData.gone()
                }
            }
        }
    }


    private fun initViews() {

        binding.recyclerView.adapter = budgetListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        bottomNavigationViewVisibility = View.VISIBLE
        toolbarVisibility = true

        binding.floatingActionButton.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToAddCashFlowFragment(
                isHomePage = true,
                type = null,
                amount = null,
                isIncome = null,
                date = null,
                documentId = null
            )
            Navigation.findNavController(it).navigate(action)
        }


        budgetListAdapter.onDeleteClick = { type, amount, isIncome, date, documentId ->

            val action = HomeFragmentDirections.actionNavigationHomeToAddCashFlowFragment(
                isHomePage = false,
                type = type,
                amount = amount,
                isIncome = isIncome,
                date = date,
                documentId = documentId
            )
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}