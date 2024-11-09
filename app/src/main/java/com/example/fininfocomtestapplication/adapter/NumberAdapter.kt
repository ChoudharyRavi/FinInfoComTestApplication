package com.example.fininfocomtestapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fininfocomtestapplication.R
import com.example.fininfocomtestapplication.databinding.ItemNumbersBinding
import com.example.fininfocomtestapplication.model.NumberItemModel
import com.example.fininfocomtestapplication.utils.Utilities


class NumberAdapter : ListAdapter<NumberItemModel, NumberAdapter.NumberViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val binding = ItemNumbersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val numberItem = getItem(position)
        holder.bind(numberItem)
    }

    inner class NumberViewHolder(private val binding: ItemNumbersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(numberItem: NumberItemModel) {
            binding.apply {
                tvNumber.apply {
                    text = numberItem.numberItem.toString()

                    val color = if (numberItem.isNumberSelected) {
                        when (numberItem.appPatterns) {
                            Utilities.AppPatterns.ODD_NUMBER ->
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color.color_odd_numbers
                                )

                            Utilities.AppPatterns.EVEN_NUMBER ->
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color.color_even_numbers
                                )

                            Utilities.AppPatterns.PRIME_NUMBER ->
                                ContextCompat.getColor(
                                itemView.context,
                                R.color.color_prime_numbers
                            )

                            Utilities.AppPatterns.FIBONACCI_SEQUENCE ->
                                ContextCompat.getColor(
                                itemView.context,
                                R.color.color_fibonacci_numbers
                            )
                        }
                    } else {
                        Color.GRAY
                    }
                    root.setBackgroundColor(color)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NumberItemModel>() {
        override fun areItemsTheSame(oldItem: NumberItemModel, newItem: NumberItemModel): Boolean {
            return oldItem.numberItem == newItem.numberItem
        }

        override fun areContentsTheSame(
            oldItem: NumberItemModel,
            newItem: NumberItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}