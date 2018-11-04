package io.synople.truffle.client

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.client.adapter.ItemAdapter
import io.synople.truffle.common.model.Item
import io.synople.truffle.common.model.Ticket


private const val TICKET = "ticket"

class TransactionHistoryFragment : Fragment() {
    private lateinit var ticket: Ticket

    val items: MutableList<Item> = mutableListOf()
    val adapter: ItemAdapter by lazy {
        ItemAdapter(items)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_transaction_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance(ticket: Ticket) = TransactionHistoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TICKET, ticket)
            }
        }
    }
}