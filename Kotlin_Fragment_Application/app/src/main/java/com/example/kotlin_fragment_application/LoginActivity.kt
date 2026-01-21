package com.example.kotlin_fragment_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val loginMap = hashMapOf(
            "admin@gmail.com" to "Admin@123",
            "user@gmail.com" to "User@123"
        )

        btnLogin.setOnClickListener {

            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
            val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{6,}$".toRegex()

            if (!emailRegex.matches(username)) {
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!passwordRegex.matches(password)) {
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (loginMap[username] == password) {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
