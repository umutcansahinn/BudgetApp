package com.example.paranikontrolet.ui.home.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paranikontrolet.data.model.Budget
import com.example.paranikontrolet.databinding.ItemHomeRecyclerviewBinding
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.toFormat
import java.util.Date

class BudgetListAdapter : RecyclerView.Adapter<BudgetListAdapter.ViewHolder>() {

    val budgetList = ArrayList<BudgetUiModel>()
    var onDeleteClick: ((type:String,amount: String,isIncome:String,date: String,documentId: String) -> Unit)? = null

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
        holder.binding.imageViewIcon.setImageResource(budgetList[position].icon)
        holder.binding.cardView.setCardBackgroundColor(Color.parseColor(budgetList[position].cardColor))

        holder.binding.imageButton.setOnClickListener {
            onDeleteClick?.invoke(
                budgetList[position].type,
                budgetList[position].amount.toString(),
                budgetList[position].isIncome.toString(),
                budgetList[position].date.toFormat(Constants.CURRENT_DATE_FORMAT),
                budgetList[position].id!!
            )
        }
    }

    fun updateList(newToDoList: List<BudgetUiModel>) {
        budgetList.clear()
        budgetList.addAll(newToDoList)
        notifyDataSetChanged()
    }
}