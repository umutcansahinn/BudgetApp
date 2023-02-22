package com.example.paranikontrolet.ui.home.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paranikontrolet.databinding.ItemHomeRecyclerviewBinding
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.utils.Constants
import com.example.paranikontrolet.utils.toFormat

class BudgetListAdapter : RecyclerView.Adapter<BudgetListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHomeRecyclerviewBinding) :
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

    var budgetList: List<BudgetUiModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

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

        holder.binding.apply {
            textViewAmount.text = budgetList[position].amount.toString()
            textViewType.text = budgetList[position].type
            textViewDate.text = budgetList[position].date.toFormat(Constants.CURRENT_DATE_FORMAT)
            imageViewIcon.setImageResource(budgetList[position].icon)
            lineColor.setBackgroundColor(Color.parseColor(budgetList[position].cardColor))

            imageButton.setOnClickListener {
                onDeleteClick?.invoke(
                    budgetList[position].type,
                    budgetList[position].amount.toString(),
                    budgetList[position].isIncome.toString(),
                    budgetList[position].date.toFormat(Constants.CURRENT_DATE_FORMAT),
                    budgetList[position].id!!
                )
            }
        }
    }

    var onDeleteClick: ((
        type: String,
        amount: String,
        isIncome: String,
        date: String,
        documentId: String
    ) -> Unit)? = null

}