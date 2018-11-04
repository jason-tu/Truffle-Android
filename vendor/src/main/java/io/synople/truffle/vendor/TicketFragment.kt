package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class TicketFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TicketFragment()
    }
}
