package com.demo.lululemon.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.lululemon.R

class ItemListAdapter :
    RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {
    private val items = ArrayList<String>()

    fun setData(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ItemViewHolder(
        private val view: View,
    ) : RecyclerView.ViewHolder(view) {
        fun bind(value: String) {
            view.findViewById<TextView>(R.id.list_tv).text = value
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}