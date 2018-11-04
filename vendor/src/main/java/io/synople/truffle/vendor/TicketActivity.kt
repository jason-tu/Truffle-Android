package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.synople.truffle.common.model.Item
import io.synople.truffle.common.model.User

class TicketActivity : AppCompatActivity() {

    val ticketFragment: TicketFragment by lazy {
        TicketFragment.newInstance()
    }

    val ticketItems: MutableList<Item> = mutableListOf()
    var selectedCustomer: User? = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        supportFragmentManager.beginTransaction()
            .replace(R.id.ticketFrame, ticketFragment).commit()
        supportFragmentManager.beginTransaction().replace(R.id.itemsFrame, ItemsFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.customersFrame, CustomerListFragment.newInstance())
            .commit()
    }

    fun notifyTicketChanged() {
        ticketFragment.addedItem()
    }
}
