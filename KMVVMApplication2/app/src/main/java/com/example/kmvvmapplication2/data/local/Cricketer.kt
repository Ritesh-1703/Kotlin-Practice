package com.example.kmvvmapplication2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cricketer_table")
data class Cricketer(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val runs: Int
)