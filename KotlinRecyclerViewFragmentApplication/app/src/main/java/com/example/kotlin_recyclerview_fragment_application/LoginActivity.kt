package com.example.kotlin_recyclerview_fragment_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val uName= findViewById<EditText>(R.id.username)
        val pword= findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.Login)

        val loginmap = hashMapOf(
            "admin@gmail.com" to "Admin@123",
            "user@gmail.com" to "User@123"
        )

        login.setOnClickListener {
            val name = uName.text.toString()
            val passw= pword.text.toString()

            if (name.isEmpty() || passw.isEmpty()) {
                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(loginmap[name]==passw){
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"Enter Valid Credentials", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

        }

    }
}