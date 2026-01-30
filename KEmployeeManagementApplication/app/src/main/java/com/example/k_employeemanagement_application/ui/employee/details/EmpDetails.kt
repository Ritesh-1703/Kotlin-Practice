package com.example.k_employeemanagement_application.ui.employee.details

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.k_employeemanagement_application.R
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity
import com.example.k_employeemanagement_application.databinding.ActivityEmpDetailsBinding
import com.example.k_employeemanagement_application.ui.employee.update.UpdateEmployee
import com.example.k_employeemanagement_application.ui.home.Home
import kotlinx.coroutines.launch

class EmpDetails : AppCompatActivity()  {
    private lateinit var binding: ActivityEmpDetailsBinding
    private lateinit var viewModel: EmployeeDetailsViewModel

    private var currentEmp: EmpEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEmpDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EmployeeDetailsViewModel::class.java]

        val empId = intent.getIntExtra("EMP_ID", -1)

        if (empId != -1) {
            viewModel.loadEmployee(empId)
        }

        viewModel.employee.observe(this) { emp ->

            currentEmp = emp

            binding.tvEmployeeName.text = emp.name
            binding.tvEmployeeId.text = "ID: ${emp.id}"
            binding.tvPosition.text = emp.position
            binding.tvDepartment.text = emp.department
            binding.tvEmail.text = emp.email
            binding.tvPhone.text = "(+91) ${emp.phone}"
            binding.tvSalary.text = "Rs ${emp.salary}"
        }


        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, UpdateEmployee::class.java)
            intent.putExtra("EMP_ID", empId)
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finish()
        }

        binding.btnDelete.setOnClickListener {
           currentEmp?.let {
               showDeleteDialog(it)
           }
        }


    }

    private fun showDeleteDialog(emp: EmpEntity) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_confirm_delete, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val btnCancel = dialogView.findViewById<androidx.appcompat.widget.AppCompatImageButton>(R.id.btnCancel)
        val btnDelete = dialogView.findViewById<androidx.appcompat.widget.AppCompatImageButton>(R.id.btnDelete)

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnDelete.setOnClickListener {
            lifecycleScope.launch {
                viewModel.deleteEmp(emp)
            }
            dialog.dismiss()
        }
        dialog.show()

    }
}