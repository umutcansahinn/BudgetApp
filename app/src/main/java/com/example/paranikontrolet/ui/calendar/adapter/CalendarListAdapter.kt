package com.example.paranikontrolet.ui.calendar.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paranikontrolet.databinding.ItemCalendarRecyclerviewBinding
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel

class CalendarListAdapter : RecyclerView.Adapter<CalendarListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCalendarRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<BudgetUiModel>() {
        override fun areItemsTheSame(oldItem: BudgetUiModel, newItem: BudgetUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BudgetUiModel, newItem: BudgetUiModel): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallback)

    var calendarList: List<BudgetUiModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCalendarRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return calendarList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textViewAmount.text = calendarList[position].amount.toString()
            textViewType.text = calendarList[position].type
            imageViewIcon.setImageResource(calendarList[position].icon)
            lineColor.setBackgroundColor(Color.parseColor(calendarList[position].cardColor))
        }
    }
}