package com.example.paranikontrolet.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.applandeo.materialcalendarview.EventDay
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentNotificationsBinding
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.ui.base.BaseFragment
import com.example.paranikontrolet.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class NotificationsFragment : BaseFragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NotificationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
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
                    }

                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {
                }
            }
        }
    }

    private fun initViews() {
        bottomNavigationViewVisibility = View.VISIBLE
        toolbarVisibility = true

        binding.calendarView.setOnDayClickListener {
            //TODO takvim üzerinden bir gün'e tıklandığında detay göster
        }
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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}