package com.exxuslee.currencyconversion.ui.favorite

import androidx.recyclerview.widget.DiffUtil

class CurrencyDiffCallback : DiffUtil.ItemCallback<Pair<String, String>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, String>, newItem: Pair<String, String>,
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, String>, newItem: Pair<String, String>,
    ): Boolean {
        return oldItem == newItem
    }
}