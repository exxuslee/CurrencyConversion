package com.exxuslee.currencyconversion.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exxuslee.currencyconversion.databinding.RecyclerSecondBinding
import com.exxuslee.domain.models.Symbols


class SecondAdapter : RecyclerView.Adapter<SecondAdapter.ViewHolder>() {

    private var radioList: List<Symbols.Symbol> = listOf()

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
            xxxTextView.text = radioList[position].xxx
            currencyTextView.text = radioList[position].name
            radioButton.isChecked = radioList[position].base
            radioButton.setOnClickListener {
                if (lastSelectedPosition != holder.adapterPosition) {
                    lastSelectedPosition = holder.adapterPosition
                    onRadioClickListener?.invoke(position)
                    notifyDataSetChanged()
                }
            }
            compoundButton.isChecked = radioList[position].check
            compoundButton.setOnClickListener {
                onPriceClickListener?.invoke(position, compoundButton.isChecked)
            }
        }
    }

    override fun getItemCount(): Int = radioList.size

    fun setData(newList: List<Symbols.Symbol>) {
        val toDoDiffUtil = CurrencyDiffCallback(radioList, newList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.radioList = newList
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}