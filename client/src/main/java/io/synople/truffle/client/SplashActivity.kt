package io.synople.truffle.client

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import io.synople.truffle.common.model.User

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val docRef = FirebaseFirestore.getInstance().collection("users").document("absCSuaFEhxFhcpzoHvr")
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val profile = documentSnapshot.toObject<User>(User::class.java)!!

            startActivity(Intent(SplashActivity@ this, CustomerActivity::class.java).putExtra("user", profile))
            finish()
        }
    }
}
