package com.exxuslee.currencyconversion.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exxuslee.currencyconversion.R
import com.exxuslee.currencyconversion.databinding.RecyclerSecondBinding


class SecondAdapter : RecyclerView.Adapter<SecondAdapter.ViewHolder>() {

    var list: ArrayMap<String, String> = arrayMapOf()
    private var lastSelectedPosition = -1

    var onPriceClickListener: ((Int) -> Unit)? = null
    var onRadioClickListener: ((Int) -> Unit)? = null

    inner class ViewHolder(val binding: RecyclerSecondBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RecyclerSecondBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            radioButton.setOnClickListener {
                lastSelectedPosition = holder.adapterPosition
                notifyDataSetChanged()
            }
            radioButton.isChecked = lastSelectedPosition == position
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: ArrayMap<String, String>) {
        val toDoDiffUtil = CurrencyDiffCallback(list, newList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.list = newList
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}