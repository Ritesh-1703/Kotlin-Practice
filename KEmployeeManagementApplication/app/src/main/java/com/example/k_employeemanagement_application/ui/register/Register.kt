package com.example.k_employeemanagement_application.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.k_employeemanagement_application.R
import com.example.k_employeemanagement_application.data.database.entity.AdminEntity
import com.example.k_employeemanagement_application.databinding.ActivityRegisterBinding
import com.example.k_employeemanagement_application.ui.login.Login


class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]



        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

        }

        binding.btnRegister.setOnClickListener {

            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            val userType = binding.spinnerUserType.selectedItem.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || userType.isEmpty()) {
                binding.etName.error = "Please enter your name"
                binding.etEmail.error = "Please enter your email"
                binding.etPassword.error = "Please enter your password"
                binding.etConfirmPassword.error = "Please confirm your password"
                if (binding.spinnerUserType.selectedItemPosition == 0) {
                    Toast.makeText(this, "Please select user type", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

            } else if (password != confirmPassword) {
                binding.etConfirmPassword.error = "Passwords do not match"
            } else {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Register")
                builder.setMessage("Are you sure you want to register?")
                builder.setPositiveButton("Yes") { dialog, _ ->

                    viewModel.insertAdmin(
                        AdminEntity(
                            username = name,
                            password = password,
                            email = email,
                            role = userType
                        )
                    )
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }
                builder.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.create().show()

            }



        }

    }
}