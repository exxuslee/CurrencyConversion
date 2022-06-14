package com.exxuslee.currencyconversion.ui.favorite

import androidx.recyclerview.widget.DiffUtil

class CurrencyDiffCallback(
    private val oldList: Map<String, String>,
    private val newList: Map<String, String>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList == newList

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList == newList
}