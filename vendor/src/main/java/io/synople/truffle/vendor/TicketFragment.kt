package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import io.synople.truffle.common.model.Ticket
import io.synople.truffle.common.model.User
import io.synople.truffle.vendor.adapter.RowItemAdapter
import kotlinx.android.synthetic.main.fragment_ticket.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


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
            if ((activity as TicketActivity).selectedCustomer != null
                && (activity as TicketActivity).selectedCustomer!! != User()
                && (activity as TicketActivity).selectedCustomer!!.name == "Jason Tu"
            ) {
                // if met threshold, do face scan, otherwise clear ticket
                ((activity as TicketActivity).supportFragmentManager.findFragmentByTag("CustomerViewFragment") as CustomerViewFragment).setupRecognize()
            } else {
                fragmentManager!!.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.customersFrame, PaymentFragment.newInstance())
                    .commit()
            }
        }

        btnClearTicket.setOnClickListener {
            clearTicket()
        }
    }

    fun clearTicket() {
        val sdf = SimpleDateFormat("MM/dd/yy HH:mm")
        var sum = 0f
        (activity as TicketActivity).ticketItems.forEach { item ->
            sum += item.price
        }

        val ticket = Ticket()
        ticket.id = UUID.randomUUID().toString()
        ticket.items.addAll((activity as TicketActivity).ticketItems)
        ticket.customerId = "absCSuaFEhxFhcpzoHvr"
        ticket.vendorId = "Frank's Central Park Hot Dog Stand"
        ticket.time = sdf.format(Date())
        ticket.amount = sum.toDouble()

        var custId = "absCSuaFEhxFhcpzoHvr"
        if ((activity as TicketActivity).selectedCustomer != null
            && (activity as TicketActivity).selectedCustomer != User()
        ) {
            custId = (activity as TicketActivity).selectedCustomer!!.id
        }

        FirebaseFirestore.getInstance()
            .collection("users")
            .document(custId) // TODO: Replace with Id from FirebaseAuth
            .get().addOnSuccessListener {
                val jason = it.toObject(User::class.java)!!
                jason.transactions.add(ticket)

                FirebaseFirestore.getInstance().collection("users")
                    .document(custId).set(jason)
            }

        
        ((activity as TicketActivity).ticketItems.clear())
        adapter.notifyDataSetChanged()
        checkout.text = "Checkout"
        (activity as TicketActivity).selectedCustomer = User()

        fragmentManager!!.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            .replace(R.id.customersFrame, CustomerListFragment.newInstance()).commit()
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
