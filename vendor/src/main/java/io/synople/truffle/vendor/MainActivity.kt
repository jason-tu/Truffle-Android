package io.synople.truffle.vendor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.ticketFrame, TicketFrame.newInstance()).commit()
        supportFragmentManager.beginTransaction().add(R.id.itemsFrame, ItemsFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction().add(R.id.customersFrame, CustomersFragment.newInstance()).commit()
    }
}
