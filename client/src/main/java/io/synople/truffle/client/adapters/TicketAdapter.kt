package io.synople.truffle.client.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.client.R
import io.synople.truffle.common.model.Ticket
import kotlinx.android.synthetic.main.card_ticket.view.*
import java.text.NumberFormat

class TicketAdapter(
    private val tickets: MutableList<Ticket>,
    private val itemClick: (Ticket) -> Unit
) : RecyclerView.Adapter<TicketAdapter.ViewHolder>() {
    class ViewHolder(private val v: View, private val itemClick: (Ticket) -> Unit) : RecyclerView.ViewHolder(v) {
        fun bind(ticket: Ticket) {
            v.tvVendor.text = ticket.vendorId
            v.tvCost.text = NumberFormat.getCurrencyInstance().format(ticket.amount)
            v.tvDate.text = ticket.time

            v.setOnClickListener {
                itemClick(ticket)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_ticket, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(tickets[pos])
    }

    override fun getItemCount() = tickets.size
}