package com.example.ko_firebaseapplicationexample.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ko_firebaseapplicationexample.R
import com.example.ko_firebaseapplicationexample.databinding.ActivityRegisterBinding
import com.example.ko_firebaseapplicationexample.utils.FirebaseUtils
import com.example.ko_firebaseapplicationexample.utils.FirebaseUtils.auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val fullName = binding.FullName.text.toString()

            FirebaseUtils.auth
                .createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                   val uid = auth.currentUser!!.uid
                    FirebaseDatabase.getInstance().reference
                        .child("users")
                        .child(uid)
                        .setValue(fullName)

                    Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, "Register Failed:- ${it.message}", Toast.LENGTH_LONG).show()
                }

        }

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}