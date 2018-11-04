package io.synople.truffle.vendor


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import io.synople.truffle.common.model.Ticket
import io.synople.truffle.common.model.User
import kotlinx.android.synthetic.main.fragment_cash.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class CashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting the balanceDue
        var sum = 0f
        (activity as TicketActivity).ticketItems.forEach {
            sum += it.price
        }
        balanceDue.text = NumberFormat.getCurrencyInstance().format(sum)

        btnSubmit.setOnClickListener {
            val builder = AlertDialog.Builder(context!!, android.R.style.Theme_Material_Dialog_Alert)
            builder.setMessage("Confirmed")
            builder.setCancelable(false)
            builder.setPositiveButton("Ok") { _, _ ->
                (activity as TicketActivity).ticketFragment.clearTicket()
            }
            builder.create().show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CashFragment()
    }
}
