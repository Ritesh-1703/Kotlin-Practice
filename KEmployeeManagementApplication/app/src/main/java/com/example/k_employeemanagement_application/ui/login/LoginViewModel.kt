package com.example.k_employeemanagement_application.ui.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.k_employeemanagement_application.data.database.Appdatabase
import com.example.k_employeemanagement_application.data.repository.AdminRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AdminRepository

    init {
        val adminDao = Appdatabase.getInstance(application).adminDao()
        repository = AdminRepository(adminDao)
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val admin = repository.getAdminByUsername(email)
            if (admin != null && admin.password == password) {

            } else {

            }

        }

    }
}