package com.exxuslee.currencyconversion.ui.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.collection.arrayMapOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exxuslee.currencyconversion.R


class SecondAdapter : RecyclerView.Adapter<SecondAdapter.SecondHolder>() {

    var list = arrayMapOf<String,String>("1" to "1.1", "2" to "2.2", "3" to "2.2", "4" to "2.2", "5" to "2.2", "6" to "2.2", "7" to "2.2", "8" to "2.2", "9" to "2.2", "11" to "2.2", "12" to "2.2", "13" to "2.2")
        set(value) {
            val callBack = CurrencyDiffCallback(list, value)
            val diffResult = DiffUtil.calculateDiff(callBack)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }
    var onPriceClickListener: ((Int) -> Unit)? = null
    var onRadioClickListener: ((Int) -> Unit)? = null
    private var selectedRadioPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_second, parent, false)
        return SecondHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SecondHolder, position: Int) {
        viewHolder.xxxName.text = list.keyAt(position)
        viewHolder.currencyName.text = list.valueAt(position).toString()

        viewHolder.currencyName.setOnClickListener {
            onPriceClickListener?.invoke(position)
            viewHolder.compoundButton.toggle()
        }

        viewHolder.radioButton.isChecked = position == selectedRadioPosition
        viewHolder.radioButton.tag = position
        viewHolder.radioButton.setOnCheckedChangeListener { _, b ->
            if (b) {
                selectedRadioPosition = viewHolder.adapterPosition
                onRadioClickListener?.invoke(position)
//                notifyDataSetChanged()
            }
        }
    }

    class SecondHolder(view: View) : RecyclerView.ViewHolder(view) {
        val xxxName: TextView = view.findViewById(R.id.xxxTextView)
        val currencyName: TextView = view.findViewById(R.id.currencyTextView)
        val compoundButton: CheckBox = view.findViewById(R.id.compoundButton)
        val radioButton: RadioButton = view.findViewById(R.id.radioButton)
    }

    override fun getItemCount() = list.size

}