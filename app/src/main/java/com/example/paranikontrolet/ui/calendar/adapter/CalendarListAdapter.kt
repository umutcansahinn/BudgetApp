package com.example.paranikontrolet.ui.calendar.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paranikontrolet.databinding.ItemCalendarRecyclerviewBinding
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.toFormat

class CalendarListAdapter : RecyclerView.Adapter<CalendarListAdapter.ViewHolder>() {

    val calendarList = ArrayList<BudgetUiModel>()

    class ViewHolder(val binding: ItemCalendarRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

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
        holder.binding.textViewAmount.text = calendarList[position].amount.toString()
        holder.binding.textViewType.text = calendarList[position].type
        holder.binding.imageViewIcon.setImageResource(calendarList[position].icon)
        holder.binding.cardView.setCardBackgroundColor(Color.parseColor(calendarList[position].cardColor))

    }

    fun updateList(newToDoList: List<BudgetUiModel>) {
        calendarList.clear()
        calendarList.addAll(newToDoList)
        notifyDataSetChanged()
    }
}