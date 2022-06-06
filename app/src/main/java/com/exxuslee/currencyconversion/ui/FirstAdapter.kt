package com.exxuslee.currencyconversion.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exxuslee.currencyconversion.R


class FirstAdapter : RecyclerView.Adapter<FirstAdapter.FirstHolder>() {


    private val list = listOf("1", "2", "3", "4", "5")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler, parent, false)
        return FirstHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FirstHolder, position: Int) {
        val shopItem = list[position]
        viewHolder.tvName.text = shopItem
        viewHolder.tvCount.text = position.toString()
    }

    override fun getItemCount() = list.size

    class FirstHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
    }

}