package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.common.model.User
import io.synople.truffle.vendor.adapter.CustomerAdapter
import kotlinx.android.synthetic.main.fragment_customer_list.*

class CustomerListFragment : Fragment() {

    val customers: MutableList<User> = mutableListOf();

    val adapter: CustomerAdapter by lazy {
        CustomerAdapter(customers) { customer ->
            fragmentManager!!.beginTransaction().replace(R.id.customersFrame, CustomerViewFragment.newInstance(customer)).commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customers.add(User("1", "Cixin Liu"))
        customers.add(User("2", "Tyrone Reese"))

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
