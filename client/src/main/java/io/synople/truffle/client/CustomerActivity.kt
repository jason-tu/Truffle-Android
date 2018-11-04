package io.synople.truffle.client

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import io.synople.truffle.common.model.Item
import io.synople.truffle.common.model.User

class CustomerActivity : FragmentActivity() {

    private lateinit var profile: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        profile = User("1", "Cixin Liu")

        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, ProfileFragment.newInstance(profile)).commit()
    }

}
