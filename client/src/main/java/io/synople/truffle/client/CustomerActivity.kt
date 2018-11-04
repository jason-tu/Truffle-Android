package io.synople.truffle.client

import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.FragmentActivity
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore
import io.synople.truffle.common.model.Item
import io.synople.truffle.common.model.User
import kotlinx.android.synthetic.main.fragment_profile.*

class CustomerActivity : FragmentActivity() {

    private lateinit var profile: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        val bScanFace = findViewById<Button>(R.id.bScanFace)
        bScanFace?.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentFrame,
                IDFaceFragment.newInstance(profile)
            ).commit()
        }

        val docRef = FirebaseFirestore.getInstance().collection("users").document("absCSuaFEhxFhcpzoHvr")
        docRef.get().addOnSuccessListener { documentSnapshot ->
            profile = documentSnapshot.toObject<User>(User::class.java)!!

            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentFrame,
                ProfileFragment.newInstance(profile)
            ).commit()
        }
        // absCSuaFEhxFhcpzoHvr

    }

}
