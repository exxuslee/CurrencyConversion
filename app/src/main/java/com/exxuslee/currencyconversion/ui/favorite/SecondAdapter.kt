package com.exxuslee.currencyconversion.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exxuslee.currencyconversion.databinding.RecyclerSecondBinding


class SecondAdapter : RecyclerView.Adapter<SecondAdapter.ViewHolder>() {

    private var radioList: ArrayMap<String, String> = arrayMapOf()

    private var lastSelectedPosition = -1

    var onPriceClickListener: ((Int, Boolean) -> Unit)? = null
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
            xxxTextView.text = radioList.keyAt(position)
            currencyTextView.text = radioList.valueAt(position).toString()
            radioButton.setOnClickListener {
                if (lastSelectedPosition != holder.adapterPosition) {
                    lastSelectedPosition = holder.adapterPosition
                    onRadioClickListener?.invoke(position)
                    notifyDataSetChanged()
                }
            }
            radioButton.isChecked = lastSelectedPosition == position
            compoundButton.setOnClickListener {
                if (compoundButton.isChecked) onPriceClickListener?.invoke(position, true)
                else onPriceClickListener?.invoke(position, false)
            }
        }
    }

    override fun getItemCount(): Int = radioList.size

    fun setData(newList: ArrayMap<String, String>) {
        val toDoDiffUtil = CurrencyDiffCallback(radioList, newList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.radioList = newList
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}