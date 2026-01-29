package com.example.k_employeemanagement_application.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.k_employeemanagement_application.data.database.dao.AdminDao
import com.example.k_employeemanagement_application.data.database.dao.EmpDao
import com.example.k_employeemanagement_application.data.database.entity.AdminEntity
import com.example.k_employeemanagement_application.data.database.entity.EmpEntity

@Database(entities = [AdminEntity::class, EmpEntity::class], version = 1, exportSchema =false)
abstract class Appdatabase : RoomDatabase() {

    abstract fun adminDao(): AdminDao
    abstract fun empDao(): EmpDao

    companion object{
        private var INSTANCE: Appdatabase? = null

        fun getInstance(context: Context): Appdatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Appdatabase::class.java,
                    "office_management_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
