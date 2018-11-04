package io.synople.truffle.vendor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import io.synople.truffle.common.model.Vendor

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        FirebaseApp.initializeApp(this)

        FirebaseFirestore.getInstance()
            .collection("vendors")
            .document("HQndq6BHW3s9Pvd0f3cQ") // TODO: Replace with Id from FirebaseAuth
            .get().addOnSuccessListener {
                AppContext.vendor = it.toObject(Vendor::class.java)!!

                startActivity(Intent(SplashActivity@ this, MainActivity::class.java))
                finish()
            }
    }
}
