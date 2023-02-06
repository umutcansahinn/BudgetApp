package com.example.paranikontrolet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentHomeBinding
import com.example.paranikontrolet.ui.base.BaseFragment
import com.example.paranikontrolet.ui.home.adapter.BudgetListAdapter
import com.example.paranikontrolet.utils.Resource
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
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBudgetFromFirestore()
        observeEvent()
        initViews()

    }

    private fun observeEvent() {
        viewModel.result.observe(viewLifecycleOwner) {
            it?.let {
                when(it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            val newList = it.data.sortedWith(compareByDescending { it.date })
                            budgetListAdapter.updateList(newList)

                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            binding.textViewError.visibility = View.GONE
                        }else {
                            binding.recyclerView.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE
                            binding.textViewError.visibility = View.GONE
                        }

                    }
                    is Resource.Error -> {
                        binding.textViewError.text = it.message.toString()


                        binding.textViewError.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding.textViewError.visibility = View.GONE
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
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
            findNavController().navigate(R.id.action_navigation_home_to_addCashFlowFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}