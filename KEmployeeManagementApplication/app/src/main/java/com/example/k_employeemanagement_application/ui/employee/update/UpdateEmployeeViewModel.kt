package com.example.k_employeemanagement_application.ui.employee.update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.k_employeemanagement_application.data.database.Appdatabase
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity
import com.example.k_employeemanagement_application.data.repository.EmpRepository
import kotlinx.coroutines.launch

class UpdateEmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EmpRepository

    private val _employee = MutableLiveData<EmpEntity>()
    val employee: LiveData<EmpEntity> = _employee


    init {
        val dao = Appdatabase.getInstance(application).empDao()
        repository = EmpRepository(dao)
    }

    fun loadEmp(empId: Int){
        viewModelScope.launch {
            val emp = repository.getEmpById(empId)
            emp?.let{
                _employee.postValue(it)
        }
        }
    }

    fun updateEmp(emp: EmpEntity) {
        viewModelScope.launch {
            repository.updateEmp(emp)
        }
    }




}