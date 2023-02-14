package com.example.paranikontrolet.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.applandeo.materialcalendarview.EventDay
import com.example.paranikontrolet.databinding.FragmentCalendarBinding
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.utils.BaseFragment
import com.example.paranikontrolet.ui.calendar.adapter.CalendarListAdapter
import com.example.paranikontrolet.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class CalendarFragment : BaseFragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalendarViewModel by viewModels()

    private val calendarListAdapter = CalendarListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
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
            when (it) {
                is Resource.Success -> {
                    it.data?.let {
                        showCalendar(it)
                        showRecyclerView(it)
                    }
                    binding.calendarView.visibility = View.VISIBLE
                    binding.textViewError.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.calendarView.visibility = View.GONE
                    binding.textViewError.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.calendarView.visibility = View.GONE
                    binding.textViewError.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initViews() {
        bottomNavigationViewVisibility = View.VISIBLE
        toolbarVisibility = true

        binding.recyclerView.adapter = calendarListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun showCalendar(list: List<BudgetUiModel>) {

        val event = ArrayList<EventDay>()

        list.forEach {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it.date.time
            event.add(EventDay(calendar, it.icon))
        }
        binding.calendarView.setEvents(event)

    }

    private fun showRecyclerView(list: List<BudgetUiModel>) {
        binding.calendarView.setOnDayClickListener { eventDay->
            list.filter { budget->
                budget.date.time == eventDay.calendar.time.time
            }.run {
                calendarListAdapter.updateList(this)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}