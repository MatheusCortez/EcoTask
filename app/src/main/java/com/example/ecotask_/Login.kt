package com.example.ecotask_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.ecotask_.databinding.ActivityMainBinding
import com.example.ecotask_.ui.MainActivity
import com.example.ecotask_.ui.MainActivity_GeneratedInjector
import com.google.android.material.textfield.TextInputEditText


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btlogar = findViewById<Button>(R.id.btnLogin)


        btlogar.setOnClickListener {
            startActivity(Intent(this@Login, MainActivity::class.java))
            finish()

        }
    }
}
