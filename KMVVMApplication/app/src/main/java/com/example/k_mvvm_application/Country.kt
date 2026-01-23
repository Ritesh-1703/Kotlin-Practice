package com.example.kmvvmapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
){
    companion object {
        const val TABLE_NAME = "country_table"
    }
}
