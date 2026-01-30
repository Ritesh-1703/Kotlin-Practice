package com.example.k_employeemanagement_application.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity
import com.example.k_employeemanagement_application.databinding.ItemEmployeeBinding
import com.example.k_employeemanagement_application.ui.employee.details.EmpDetails


class EmployeeAdapter(
    private val onItemClick: (EmpEntity) -> Unit,
    private val onDeleteClick: (EmpEntity) -> Unit,
    private val onEditClick: (EmpEntity) -> Unit
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>(){

    private var list = emptyList<EmpEntity>()

    inner class EmployeeViewHolder(
        val binding: ItemEmployeeBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val emp = list[position]

        // bind data
        holder.binding.tvEmployeeName2.text = emp.name
        holder.binding.tvEmployeePosition2.text = emp.position
        holder.binding.tvEmployeeDepartment2.text = emp.department

        // 🔘 EDIT BUTTON
        holder.binding.btnEdit.setOnClickListener {
            onEditClick(emp)
        }

        // 🗑 DELETE BUTTON
        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(emp)
        }
        holder.itemView.setOnClickListener {
            onItemClick(emp)
        }
    }


    override fun getItemCount(): Int {
        return list.size

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(employees: List<EmpEntity>){
        list=employees
        notifyDataSetChanged()
    }
}

