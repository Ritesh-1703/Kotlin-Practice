package com.example.k_employeemanagement_application.ui.employee.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.k_employeemanagement_application.data.database.Appdatabase
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity
import com.example.k_employeemanagement_application.data.repository.EmpRepository
import kotlinx.coroutines.launch

class AddEmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EmpRepository

    init {
        val dao = Appdatabase.getInstance(application).empDao()
        repository = EmpRepository(dao)
    }

    fun insertEmp(emp: EmpEntity) {
        viewModelScope.launch {
            repository.insertEmp(emp)
        }
    }



}