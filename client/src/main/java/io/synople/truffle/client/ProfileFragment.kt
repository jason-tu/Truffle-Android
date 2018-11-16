package io.synople.truffle.client

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.client.adapters.TicketAdapter
import io.synople.truffle.common.model.Ticket
import io.synople.truffle.common.model.User
import kotlinx.android.synthetic.main.fragment_profile.*


private const val USER = "user"

class ProfileFragment : Fragment() {
    private lateinit var user: User

    private lateinit var tickets: MutableList<Ticket>
    private lateinit var adapter: TicketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(USER)!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_profile, container, false)!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tickets = mutableListOf()
        adapter = TicketAdapter(tickets) { ticket ->

        }

        tvUserName.text = user.name

        btnFaceSecurity.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(
                R.id.fragmentFrame,
                IDFaceFragment.newInstance(user)
            ).commit()
        }

        val temp = user.transactions
        temp.reverse()
        tickets.addAll(temp)
        adapter.notifyDataSetChanged()

        rvTransactionHistory.adapter = adapter
        rvTransactionHistory.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(user: User) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER, user)
            }
        }
    }
}
