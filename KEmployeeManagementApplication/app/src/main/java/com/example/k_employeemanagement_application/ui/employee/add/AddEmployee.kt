package com.example.k_employeemanagement_application.ui.employee.add

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.k_employeemanagement_application.R
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity
import com.example.k_employeemanagement_application.databinding.ActivityAddEmployeeBinding
import com.example.k_employeemanagement_application.ui.home.Home

class AddEmployee : AppCompatActivity() {

    private  lateinit var viewModel: AddEmployeeViewModel
    private lateinit var binding: ActivityAddEmployeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AddEmployeeViewModel::class.java]

        binding.btnSave.setOnClickListener {
            val name = binding.etEmployeeName.text.toString()
            val position = binding.etPosition.text.toString()
            val department = binding.etDepartment.text.toString()
            val email = binding.etEmail.text.toString()
            val phone = binding.etPhone.text.toString()
            val salary = binding.etSalary.text.toString()

            when{
                name.isEmpty() -> {
                    binding.etEmployeeName.error = "Please enter your name"
                }
                position.isEmpty() -> {
                    binding.etPosition.error = "Please enter your position"
                }
                department.isEmpty() -> {
                    binding.etDepartment.error = "Please enter your department"
                }
                email.isEmpty()->{
                    binding.etEmail.error = "Please enter your email"
                }
                phone.isEmpty()->{
                    binding.etPhone.error = "Please enter your phone"
                }
                salary.isEmpty()->{
                    binding.etSalary.error = "Please enter your salary"
                }
            }

            viewModel.insertEmp(
                EmpEntity(
                    name = name,
                    position = position,
                    department = department,
                    email = email,
                    phone = phone,
                    salary = salary.toDouble()
                )
            )
            startActivity(Intent(this, Home::class.java))
            finish()

        }
    }
}