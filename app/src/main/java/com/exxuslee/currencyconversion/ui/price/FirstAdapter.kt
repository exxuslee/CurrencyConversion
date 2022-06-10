package com.exxuslee.currencyconversion.ui.price

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exxuslee.currencyconversion.R
import com.exxuslee.domain.models.Price


class FirstAdapter :
    ListAdapter<Pair<String, Double>, FirstAdapter.FirstHolder>(RatesDiffCallback()) {

    var onPriceClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler, parent, false)
        return FirstHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FirstHolder, position: Int) {
        viewHolder.tvName.text = getItem(position).first
        viewHolder.tvCount.text = getItem(position).second.toString()
        viewHolder.itemView.setOnClickListener {
            onPriceClickListener?.invoke(position)
        }
    }

    fun updateAdapter(price: Price?) {
        submitList(price?.rates?.toList())
    }

    class FirstHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
    }

}