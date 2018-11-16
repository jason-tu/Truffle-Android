package io.synople.truffle.client

import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.FragmentActivity
import android.widget.Button
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import io.synople.truffle.common.model.Item
import io.synople.truffle.common.model.User
import kotlinx.android.synthetic.main.fragment_profile.*

class CustomerActivity : FragmentActivity() {

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        FirebaseApp.initializeApp(this)

        user = intent?.extras?.getParcelable("user")!!
        supportFragmentManager.beginTransaction().replace(
            R.id.fragmentFrame,
            ProfileFragment.newInstance(user)
        ).commit()
    }
}
