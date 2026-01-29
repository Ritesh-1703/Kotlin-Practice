package com.example.k_employeemanagement_application.data.repository

import com.example.k_employeemanagement_application.data.database.dao.AdminDao
import com.example.k_employeemanagement_application.data.database.entity.AdminEntity

class AdminRepository(private val adminDao: AdminDao) {

    suspend fun insertAdmin(admin: AdminEntity) {
        adminDao.insertAdmin(admin)
    }

    suspend fun updateAdmin(admin: AdminEntity) {
        adminDao.updateAdmin(admin)
    }

    suspend fun deleteAdmin(admin: AdminEntity) {
        adminDao.deleteAdmin(admin)
    }

    suspend fun getAdminByUsername(username: String): AdminEntity? {
        return adminDao.getAdminByUsername(username)
    }


}