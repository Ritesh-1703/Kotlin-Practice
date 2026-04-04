package com.example.roomdatabasejetpack.database

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val dueDate: Long,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
)

enum class Priority{
    LOW, HIGH,MEDIUM;

    fun getColor() = when(this){
        LOW -> Color.Green
        HIGH -> Color.Red
        MEDIUM -> Color.Yellow
    }
}