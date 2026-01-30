package com.example.k_employeemanagement_application.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity

@Dao
interface EmpDao {

    @Insert
    suspend fun insertEmp(emp: EmpEntity)

    @Update
    suspend fun updateEmp(emp: EmpEntity)

    @Delete
    suspend fun deleteEmp(emp: EmpEntity)

    @Query("SELECT * FROM employee_table ORDER BY name ASC")
     fun getAllEmp(): LiveData<List<EmpEntity>>

    @Query("SELECT * FROM employee_table WHERE id = :id")
    suspend fun getEmpById(id: Int): EmpEntity?


}