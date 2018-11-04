package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.vendor.adapter.CardItemAdapter
import kotlinx.android.synthetic.main.fragment_items.*

class ItemsFragment : Fragment() {

    private val itemAdapter: CardItemAdapter by lazy {
        CardItemAdapter(AppContext.vendor.items) { item ->
            (activity as TicketActivity).ticketItems.add(item)
            (activity as TicketActivity).notifyTicketChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvItems.adapter = itemAdapter
        rvItems.layoutManager = GridLayoutManager(context, 4)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemsFragment()
    }
}
