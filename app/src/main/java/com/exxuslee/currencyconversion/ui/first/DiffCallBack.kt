package com.exxuslee.currencyconversion.ui.first

import androidx.collection.ArrayMap
import androidx.recyclerview.widget.DiffUtil
import com.exxuslee.domain.models.Price

class DiffCallBack(
    private val oldList: ArrayMap<String, Double>,
    private val newList: ArrayMap<String, Double>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList.keyAt(oldItemPosition) == newList.keyAt(oldItemPosition)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }
}