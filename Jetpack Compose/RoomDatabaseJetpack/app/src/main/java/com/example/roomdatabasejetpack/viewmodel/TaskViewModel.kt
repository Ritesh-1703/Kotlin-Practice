package com.example.roomdatabasejetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasejetpack.database.Priority
import com.example.roomdatabasejetpack.database.TaskEntity
import com.example.roomdatabasejetpack.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository
): ViewModel(){

    val allTasks: StateFlow<List<TaskEntity>> = repository.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val pendingCount: StateFlow<Int> = repository.getPendingTaskCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _showOnlyPending = MutableStateFlow(false)
    val showOnlyPending: StateFlow<Boolean> = _showOnlyPending.asStateFlow()

    val filteredTasks: StateFlow<List<TaskEntity>> = combine(
        allTasks,
        searchQuery,
        showOnlyPending
    ) { tasks, query, onlyPending ->
        tasks.filter { task ->
            (task.title.contains(query, ignoreCase = true) ||
                    task.description.contains(query, ignoreCase = true)) &&
                    (!onlyPending || !task.isCompleted)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Selected task for detail/edit
    private val _selectedTask = MutableStateFlow<TaskEntity?>(null)
    val selectedTask: StateFlow<TaskEntity?> = _selectedTask.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleFilter() {
        _showOnlyPending.value = !_showOnlyPending.value
    }

    fun selectTask(task: TaskEntity) {
        _selectedTask.value = task
    }

    fun clearSelectedTask() {
        _selectedTask.value = null
    }

    fun addTask(title: String, description: String, priority: Priority, dueDate: Long) {
        viewModelScope.launch {
            val task = TaskEntity(
                title = title,
                description = description,
                priority = priority,
                dueDate = dueDate,
            )
            repository.addTask(task)
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun toggleTaskCompletion(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun deleteCompletedTasks() {
        viewModelScope.launch {
            repository.deleteCompletedTasks()
        }
    }
    fun getTaskById(taskId: Int): StateFlow<TaskEntity?> {
        return repository.getTaskById(taskId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )
    }
}