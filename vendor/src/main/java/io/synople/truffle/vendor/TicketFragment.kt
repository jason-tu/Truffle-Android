package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.vendor.adapter.RowItemAdapter
import kotlinx.android.synthetic.main.fragment_ticket.*


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

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TicketFragment()
    }
}
