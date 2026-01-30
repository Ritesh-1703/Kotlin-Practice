package com.example.k_employeemanagement_application.data.repository

import com.example.k_employeemanagement_application.data.database.dao.EmpDao
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity

class EmpRepository(private  val empDao: EmpDao){
    val allEmp = empDao.getAllEmp()

    suspend fun insertEmp(emp: EmpEntity){
        empDao.insertEmp(emp)
    }

    suspend fun updateEmp(emp: EmpEntity){
        empDao.updateEmp(emp)
    }

    suspend fun deleteEmp(emp: EmpEntity){
        empDao.deleteEmp(emp)
    }

    suspend fun getEmpById(id: Int): EmpEntity?{
        return empDao.getEmpById(id)
    }




}