package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_cash.*
import java.text.NumberFormat

class CreditFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_credit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting the balanceDue
        var sum = 0f
        (activity as TicketActivity).ticketItems.forEach {
            sum += it.price
        }
        balanceDue.text = NumberFormat.getCurrencyInstance().format(sum)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreditFragment()
    }
}
