package com.exxuslee.currencyconversion.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.collection.arrayMapOf
import androidx.recyclerview.widget.RecyclerView
import com.exxuslee.currencyconversion.R
import com.exxuslee.domain.models.Price


class FirstAdapter : RecyclerView.Adapter<FirstAdapter.FirstHolder>() {


    private var list = arrayMapOf<String,Double>("1" to 1.1, "2" to 2.2)

    var onPriceClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler, parent, false)
        return FirstHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FirstHolder, position: Int) {
        viewHolder.tvName.text = list.keyAt(position)
        viewHolder.tvCount.text = list.valueAt(position).toString()
        viewHolder.itemView.setOnClickListener {
            onPriceClickListener?.invoke(position)
        }
    }

    override fun getItemCount() = list.size

    fun updateAdapter(price: Price?) {
        if (price != null) {
            list = price.rates
            notifyDataSetChanged()
        }
    }

    class FirstHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
    }

}