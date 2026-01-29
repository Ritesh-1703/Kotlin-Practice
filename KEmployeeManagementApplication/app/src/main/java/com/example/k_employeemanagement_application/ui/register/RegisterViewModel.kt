package com.example.k_employeemanagement_application.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.k_employeemanagement_application.data.database.Appdatabase
import com.example.k_employeemanagement_application.data.database.dao.AdminDao
import com.example.k_employeemanagement_application.data.database.entity.AdminEntity
import com.example.k_employeemanagement_application.data.repository.AdminRepository
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application): AndroidViewModel(application) {

   private val repository: AdminRepository

   init {
       val adminDao = Appdatabase.getInstance(application).adminDao()
       repository= AdminRepository(adminDao)
   }


  fun insertAdmin(admin: AdminEntity) {
        viewModelScope.launch {
            repository.insertAdmin(admin)

        }
  }








}