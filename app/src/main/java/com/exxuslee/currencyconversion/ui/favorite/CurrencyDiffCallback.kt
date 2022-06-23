package com.exxuslee.currencyconversion.ui.favorite

import androidx.recyclerview.widget.DiffUtil
import com.exxuslee.domain.models.Symbols

class CurrencyDiffCallback(
    private val oldList: List<Symbols.Symbol>,
    private val newList: List<Symbols.Symbol>,
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