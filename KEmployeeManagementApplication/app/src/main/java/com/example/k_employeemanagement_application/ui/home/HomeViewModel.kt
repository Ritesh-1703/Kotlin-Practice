package com.example.k_employeemanagement_application.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.k_employeemanagement_application.data.database.Appdatabase
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity
import com.example.k_employeemanagement_application.data.repository.EmpRepository

class HomeViewModel (application: Application) : AndroidViewModel(application) {
private val repository: EmpRepository
val allEmployee : LiveData<List<EmpEntity>>

    init {
        val dao = Appdatabase.getInstance(application).empDao()
        repository = EmpRepository(dao)
        allEmployee = repository.allEmp
    }

    suspend fun deleteEmp(emp: EmpEntity){
        repository.deleteEmp(emp)
    }
     suspend fun updateEmp(emp: EmpEntity){
        repository.updateEmp(emp)
    }




}