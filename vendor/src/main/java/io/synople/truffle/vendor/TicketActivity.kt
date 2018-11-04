package io.synople.truffle.vendor

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import io.synople.truffle.common.model.Item

class TicketActivity : FragmentActivity() {

    private val ticketFragment: TicketFragment by lazy {
        TicketFragment.newInstance()
    }

    val ticketItems: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        supportFragmentManager.beginTransaction()
            .replace(R.id.ticketFrame, ticketFragment).commit()
        supportFragmentManager.beginTransaction().replace(R.id.itemsFrame, ItemsFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.customersFrame, CustomerListFragment.newInstance()).commit()
    }

    fun notifyTicketChanged() {
        ticketFragment.addedItem()
    }
}
