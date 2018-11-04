package io.synople.truffle.client.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.client.R
import io.synople.truffle.common.model.Item
import io.synople.truffle.common.model.Ticket

class ItemAdapter(
    private val items: MutableList<Item>
): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(private val v: View): RecyclerView.ViewHolder(v) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
    }

    override fun getItemCount() = items.size
}