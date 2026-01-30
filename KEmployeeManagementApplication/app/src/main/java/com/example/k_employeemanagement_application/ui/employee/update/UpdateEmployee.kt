package com.example.k_employeemanagement_application.ui.employee.update

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity
import com.example.k_employeemanagement_application.databinding.ActivityUpdateEmployeBinding
import com.example.k_employeemanagement_application.ui.home.Home

class UpdateEmployee : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateEmployeBinding
    private lateinit var viewModel: UpdateEmployeeViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateEmployeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[UpdateEmployeeViewModel::class.java]

        binding.btnCancel.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finish()
        }

        val empId = intent.getIntExtra("EMP_ID", -1)

        if (empId == -1) {
            Toast.makeText(this, "Invalid employee", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        viewModel.loadEmp(empId)

        viewModel.employee.observe(this) { emp ->
            binding.etEmployeeName.setText(emp.name)
            binding.etPosition.setText(emp.position)
            binding.etDepartment.setText(emp.department)
            binding.etEmail.setText(emp.email)
            binding.etPhone.setText(emp.phone)
            binding.etSalary.setText(emp.salary.toString())
        }

        binding.btnUpdate.setOnClickListener {

            val updateEmployee = EmpEntity(
                id = empId,
                name = binding.etEmployeeName.text.toString(),
                position = binding.etPosition.text.toString(),
                department = binding.etDepartment.text.toString(),
                email = binding.etEmail.text.toString(),
                phone = binding.etPhone.text.toString(),
                salary = binding.etSalary.text.toString().toDouble(),
            )
                viewModel.updateEmp(updateEmployee)

            Toast.makeText(this, "Employee updated successfully", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, Home::class.java))
                finish()


        }

        binding.btnCancel.setOnClickListener{
            startActivity(Intent(this, Home::class.java))
            finish()
        }

    }
}