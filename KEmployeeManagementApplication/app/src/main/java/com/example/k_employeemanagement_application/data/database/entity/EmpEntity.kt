package com.example.k_employeemanagement_application.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "employee_table")
data class EmpEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val position: String,
    val department: String,
    val email: String,
    val phone: String,
    val salary: Double
) : Parcelable
