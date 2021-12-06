package com.example.ecotask_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class logo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        Handler().postDelayed({

               startActivity(Intent(this@logo, Login::class.java))
            finish()
        }, 5000)
    }
}