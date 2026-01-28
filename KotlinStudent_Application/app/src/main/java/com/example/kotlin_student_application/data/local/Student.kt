package com.example.kotlin_student_application.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "student_class")
    val studentclass: String ,

    @ColumnInfo(name = "marks")
        val marks: Int,


    @ColumnInfo(name = "create_at", defaultValue = "CURRENT_TIMESTAMP")
    val createat : Long = System.currentTimeMillis()

)
