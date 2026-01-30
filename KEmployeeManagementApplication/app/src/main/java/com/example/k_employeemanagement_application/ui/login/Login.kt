package com.example.k_employeemanagement_application.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.k_employeemanagement_application.R
import com.example.k_employeemanagement_application.databinding.ActivityLoginBinding
import com.example.k_employeemanagement_application.ui.home.Home
import com.example.k_employeemanagement_application.ui.register.Register
import com.example.k_employeemanagement_application.utils.SessionManager


class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        sessionManager= SessionManager(this)

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        viewModel.loginSuccess.observe(this) { success ->
            if (!success) {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.loggedInAdmin.observe(this){admin ->
            admin?.let {
                sessionManager.saveSassion(
                    username = it.username,
                    email = it.email,
                    role = it.role,
                    isLoggedIn = true
                )
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Home::class.java))
                finish()

            }
        }






        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            when{
                email.isEmpty() -> {
                    binding.etEmail.error = "Please enter your email"
                }
                password.isEmpty() -> {
                    binding.etPassword.error = "Please enter your password"
                }
                else->{
                    viewModel.login(email, password)
                }
            }




        }


    }
}