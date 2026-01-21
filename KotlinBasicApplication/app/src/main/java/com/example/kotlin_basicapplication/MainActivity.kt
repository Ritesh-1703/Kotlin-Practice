package com.example.kotlin_basicapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // HashMap for login
        val loginMap = HashMap<String, String>()
        loginMap["admin@gmail.com"] = "Admin@123"
        loginMap["user@gmail.com"] = "User@123"

        btnLogin.setOnClickListener {

            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Empty validation
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Email regex
            val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()

            // Password regex (basic)
            val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{6,}$".toRegex()

            if (!emailRegex.matches(username)) {
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!passwordRegex.matches(password)) {
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Login check
            if (loginMap.containsKey(username) && loginMap[username] == password) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }



        }

}


