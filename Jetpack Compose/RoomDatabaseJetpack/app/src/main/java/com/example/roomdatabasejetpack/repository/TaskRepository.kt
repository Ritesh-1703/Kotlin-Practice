package com.example.roomdatabasejetpack.repository

import com.example.roomdatabasejetpack.database.TaskDao
import com.example.roomdatabasejetpack.database.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao){

    fun getAllTasks(): Flow<List<TaskEntity>> = taskDao.getAllTasks()

    fun getTaskById(id: Int): Flow<TaskEntity> = taskDao.getTaskById(id)

    fun getPendingTask():Flow<List<TaskEntity>> = taskDao.getTasksByCompletion(false)
    fun getCompletedTask():Flow<List<TaskEntity>> = taskDao.getTasksByCompletion(true)

    fun searchTasks(query: String): Flow<List<TaskEntity>> = taskDao.searchTasks(query)

    fun getPendingTaskCount(): Flow<Int> = taskDao.getPendingTaskCount()

    suspend fun addTask(task: TaskEntity) = taskDao.insertTask(task)

    suspend fun updateTask(task: TaskEntity) = taskDao.updateTask(task)

    suspend fun deleteTask(task: TaskEntity) = taskDao.deleteTask(task)

    suspend fun deleteCompletedTasks() = taskDao.deleteCompletedTasks()
}