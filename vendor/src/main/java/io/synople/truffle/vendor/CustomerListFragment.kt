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
import com.google.firebase.firestore.FirebaseFirestore


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

        // TODO: Populate customers
        val docRef = FirebaseFirestore.getInstance().collection("users").document("T3ZwXPVjVseIZnHgr1Vw")
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val cixin = documentSnapshot.toObject<User>(User::class.java)

            val docRef2 = FirebaseFirestore.getInstance().collection("users").document("absCSuaFEhxFhcpzoHvr")
            docRef2.get().addOnSuccessListener { documentSnapshot2 ->
                val jason = documentSnapshot2.toObject<User>(User::class.java)

                customers.add(cixin!!)
                customers.add(jason!!)

                activity!!.runOnUiThread { adapter.notifyDataSetChanged() }
            }
        }

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
