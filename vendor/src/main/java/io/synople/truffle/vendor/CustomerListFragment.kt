package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.common.model.Item
import io.synople.truffle.common.model.Ticket
import io.synople.truffle.common.model.User
import io.synople.truffle.vendor.adapter.CustomerAdapter
import kotlinx.android.synthetic.main.fragment_customer_list.*
import java.util.*

class CustomerListFragment : Fragment() {

    val customers: MutableList<User> = mutableListOf()

    val adapter: CustomerAdapter by lazy {
        CustomerAdapter(customers) { customer ->
            (activity as TicketActivity).selectedCustomer = customer

            fragmentManager!!.beginTransaction()
                .replace(R.id.customersFrame, CustomerViewFragment.newInstance(customer)).commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val items = mutableListOf<Item>()
        items.add(Item(UUID.randomUUID().toString(), "Test Item", 1.5f))

        val userOneTransactions = mutableListOf<Ticket>()
        userOneTransactions.add(
            Ticket(
                UUID.randomUUID().toString(),
                items,
                "customerId",
                "vendorId",
                "Time",
                1337.0
            )
        )
        customers.add(User("1", "Cixin Liu", userOneTransactions))
        customers.add(User("2", "Tyrone Reese", userOneTransactions))

        return inflater.inflate(R.layout.fragment_customer_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCustomerList.adapter = adapter
        rvCustomerList.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CustomerListFragment()
    }
}
