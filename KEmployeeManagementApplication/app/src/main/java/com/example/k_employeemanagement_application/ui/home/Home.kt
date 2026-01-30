package com.example.k_employeemanagement_application.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_employeemanagement_application.R
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity
import com.example.k_employeemanagement_application.databinding.ActivityHomeBinding
import com.example.k_employeemanagement_application.ui.employee.add.AddEmployee
import com.example.k_employeemanagement_application.ui.employee.details.EmpDetails
import com.example.k_employeemanagement_application.ui.employee.update.UpdateEmployee
import kotlinx.coroutines.launch

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: EmployeeAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.fabAddEmployee.setOnClickListener {
            startActivity(Intent(this, AddEmployee::class.java))

        }
        val recyclerView = binding.rvEmployees

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = EmployeeAdapter(
            onItemClick = {emp->
                val intent = Intent(this, EmpDetails::class.java)
                intent.putExtra("EMP_ID", emp.id)
               startActivity(intent)
            },
            onDeleteClick = {
                showDeleteDialog(it)
            },
            onEditClick = {
                val intent = Intent(this, UpdateEmployee::class.java)
                intent.putExtra("Emp_ID",it.id)
                startActivity(intent)
            }
        )
        recyclerView.adapter = adapter


        viewModel.allEmployee.observe(this){
            adapter.setData(it)
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