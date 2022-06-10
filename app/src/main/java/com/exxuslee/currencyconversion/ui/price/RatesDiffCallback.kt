package com.exxuslee.currencyconversion.ui.price

import androidx.recyclerview.widget.DiffUtil

class RatesDiffCallback : DiffUtil.ItemCallback<Pair<String, Double>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, Double>, newItem: Pair<String, Double>,
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, Double>, newItem: Pair<String, Double>,
    ): Boolean {
       return oldItem == newItem
    }
}