package com.example.paranikontrolet.ui.home.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.example.paranikontrolet.R
import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.databinding.ItemHomeRecyclerviewBinding
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.toFormat

class BudgetListAdapter : RecyclerView.Adapter<BudgetListAdapter.ViewHolder>() {

    val budgetList = ArrayList<Budget>()

    class ViewHolder(val binding: ItemHomeRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return budgetList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewAmount.text = budgetList[position].amount.toString()
        holder.binding.textViewType.text = budgetList[position].type
        holder.binding.textViewDate.text =
            budgetList[position].date.toFormat(Constants.CURRENT_DATE_FORMAT)
        if (budgetList[position].isIncome) {
            holder.binding.cardView.setStrokeColor(Color.GREEN)
            holder.binding.textViewAmount.setTextColor(Color.GREEN)
        } else {
            holder.binding.cardView.setStrokeColor(Color.RED)
            holder.binding.textViewAmount.setTextColor(Color.RED)
        }
    }

    fun updateList(newToDoList: List<Budget>) {
        budgetList.clear()
        budgetList.addAll(newToDoList)
        notifyDataSetChanged()
    }
}