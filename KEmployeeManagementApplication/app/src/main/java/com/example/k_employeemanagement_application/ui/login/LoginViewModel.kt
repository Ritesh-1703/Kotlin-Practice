package com.example.k_employeemanagement_application.ui.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.k_employeemanagement_application.data.database.Appdatabase
import com.example.k_employeemanagement_application.data.database.entity.AdminEntity
import com.example.k_employeemanagement_application.data.repository.AdminRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AdminRepository

    val loginSuccess = MutableLiveData<Boolean>()
    val loggedInAdmin = MutableLiveData<AdminEntity?>()

    init {
        val adminDao = Appdatabase.getInstance(application).adminDao()
        repository = AdminRepository(adminDao)
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val admin = repository.getAdminByUsername(email)
            if (admin != null && admin.password == password) {
                    loggedInAdmin.postValue(admin)
                loginSuccess.postValue(true)
            } else {
                loginSuccess.postValue(false)
            }

        }

    }
}