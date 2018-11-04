package io.synople.truffle.vendor.adapter

import android.content.ClipData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.vendor.R
import kotlinx.android.synthetic.main.card_customer.view.*

class CustomerAdapter(
    private val items: MutableList<ClipData.Item>,
    private val itemClick: (ClipData.Item) -> Unit
): RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
    class ViewHolder(private val v: View, private val itemClick: (ClipData.Item) -> Unit): RecyclerView.ViewHolder(v) {
        fun bind(item: ClipData.item) {
            v.tvCustomerName.text = item.name

            v.setOnClickListener {
                itemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_customer, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(items[pos])
    }

    override fun getItemCount() = items.size
}