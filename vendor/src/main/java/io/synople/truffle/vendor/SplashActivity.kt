package io.synople.truffle.vendor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // TODO: Load data from Firebase

        startActivity(Intent(SplashActivity@this, MainActivity::class.java))
        finish()
    }
}
