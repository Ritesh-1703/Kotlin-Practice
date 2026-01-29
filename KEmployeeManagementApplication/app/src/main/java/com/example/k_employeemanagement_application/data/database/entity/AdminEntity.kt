package com.example.k_employeemanagement_application.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admin_table")
data class AdminEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val password: String,
    val email: String,
    val role: String
)

