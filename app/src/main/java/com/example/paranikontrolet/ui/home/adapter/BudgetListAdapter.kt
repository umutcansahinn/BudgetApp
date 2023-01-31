package com.example.paranikontrolet.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.databinding.ItemHomeRecyclerviewBinding

class BudgetListAdapter: RecyclerView.Adapter<BudgetListAdapter.ViewHolder>() {

    val budgetList = ArrayList<Budget>()

    class ViewHolder(val binding: ItemHomeRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

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

    }

    fun updateList(newToDoList: List<Budget>) {
        budgetList.clear()
        budgetList.addAll(newToDoList)
        notifyDataSetChanged()
    }
}