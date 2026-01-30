package com.example.k_employeemanagement_application.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.k_employeemanagement_application.data.database.entity.AdminEntity

@Dao
interface AdminDao {

    @Insert
    suspend fun insertAdmin(admin: AdminEntity)

    @Update
    suspend fun updateAdmin(admin: AdminEntity)

    @Delete
    suspend fun deleteAdmin(admin: AdminEntity)

    @Query("SELECT * FROM admin_table WHERE email = :email")
    suspend fun getAdminByUsername(email: String): AdminEntity?


}