package com.exxuslee.currencyconversion.ui.favorite

import androidx.collection.ArrayMap
import androidx.recyclerview.widget.DiffUtil

class CurrencyDiffCallback(
    private val oldList: ArrayMap<String, String>,
    private val newList: ArrayMap<String, String>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList.keyAt(oldItemPosition) == newList.keyAt(oldItemPosition)

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList.valueAt(oldItemPosition) == newList.valueAt(oldItemPosition)
    }