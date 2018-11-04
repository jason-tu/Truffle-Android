package io.synople.truffle.vendor.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.common.model.Item
import io.synople.truffle.vendor.R
import kotlinx.android.synthetic.main.row_item.view.*
import java.text.NumberFormat

class RowItemAdapter(
    private val items: MutableList<Item>,
    private val itemClick: (Item) -> Unit
) : RecyclerView.Adapter<RowItemAdapter.ViewHolder>() {
    class ViewHolder(private val v: View, private val itemClick: (Item) -> Unit) : RecyclerView.ViewHolder(v) {
        fun bind(item: Item) {
            v.tvItemName.text = item.name
            v.tvItemPrice.text = NumberFormat.getCurrencyInstance().format(item.price)
            v.setOnClickListener { itemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(items[pos])
    }

    override fun getItemCount() = items.size
}