package com.example.k_employeemanagement_application.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.k_employeemanagement_application.R
import com.example.k_employeemanagement_application.databinding.ActivityLoginBinding
import com.example.k_employeemanagement_application.ui.register.Register


class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
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