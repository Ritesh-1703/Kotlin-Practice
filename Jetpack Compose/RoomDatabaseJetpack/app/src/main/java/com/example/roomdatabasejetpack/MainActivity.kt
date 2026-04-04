package com.example.roomdatabasejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdatabasejetpack.database.TaskDatabase
import com.example.roomdatabasejetpack.navigation.TaskNavigation
import com.example.roomdatabasejetpack.repository.TaskRepository
import com.example.roomdatabasejetpack.ui.theme.TaskAppTheme
import com.example.roomdatabasejetpack.viewmodel.TaskViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Database
        val database = TaskDatabase.getInstance(this)
        val repository = TaskRepository(database.taskDao())

        setContent {
            TaskAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: TaskViewModel = viewModel(
                        factory = TaskViewModelFactory(repository)
                    )
                    TaskNavigation(viewModel = viewModel)
                }
            }
        }
    }
}

// ViewModel Factory


class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}