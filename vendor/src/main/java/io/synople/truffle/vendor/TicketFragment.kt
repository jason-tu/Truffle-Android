package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import io.synople.truffle.vendor.adapter.RowItemAdapter
import kotlinx.android.synthetic.main.fragment_ticket.*
import java.text.NumberFormat


class TicketFragment : Fragment() {

    val adapter: RowItemAdapter by lazy {
        RowItemAdapter((activity as TicketActivity).ticketItems) {
            // TODO: Something? Confirm Delete?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTicket.adapter = adapter
        rvTicket.layoutManager = LinearLayoutManager(context)

        checkout.setOnClickListener {
            fragmentManager!!.beginTransaction()
                .replace(R.id.customersFrame, PaymentFragment.newInstance())
                .commit()
        }

        btnClearTicket.setOnClickListener {
            ((activity as TicketActivity).ticketItems.clear())
            adapter.notifyDataSetChanged()
            checkout.text = "Checkout"

            fragmentManager!!.beginTransaction()
                .replace(R.id.customersFrame, CustomersFragment.newInstance()).commit()
        }
    }

    fun addedItem() {
        adapter.notifyItemInserted((activity as TicketActivity).ticketItems.size - 1)

        // Update checkout amount
        var ticketSum = 0f
        ((activity as TicketActivity).ticketItems).forEach { ticket ->
            ticketSum += ticket.price
        }

        checkout.text = "Checkout " + NumberFormat.getCurrencyInstance().format(ticketSum)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TicketFragment()
    }
}
