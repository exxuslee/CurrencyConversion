package com.exxuslee.currencyconversion.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exxuslee.currencyconversion.R
import com.exxuslee.domain.models.Symbols


class SecondAdapter :
    ListAdapter<Pair<String, String>, SecondAdapter.FirstHolder>(CurrencyDiffCallback()) {

    var onPriceClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_second, parent, false)
        return FirstHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FirstHolder, position: Int) {
        viewHolder.xxxName.text = getItem(position).first
        viewHolder.currencyName.text = getItem(position).second
        viewHolder.itemView.setOnClickListener {
            onPriceClickListener?.invoke(position)
            viewHolder.tvCount.toggle()
        }
    }

    fun updateAdapter(symbols: Symbols?) {
        submitList(symbols?.currency?.toList())
    }

    class FirstHolder(view: View) : RecyclerView.ViewHolder(view) {
        val xxxName: TextView = view.findViewById(R.id.xxxTextView)
        val currencyName: TextView = view.findViewById(R.id.currencyTextView)
        val tvCount: CheckBox = view.findViewById(R.id.compoundButton)

    }

}