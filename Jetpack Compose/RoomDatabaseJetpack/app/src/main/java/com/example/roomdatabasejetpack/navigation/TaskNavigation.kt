package com.example.roomdatabasejetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomdatabasejetpack.screens.AddEditTaskScreen
import com.example.roomdatabasejetpack.screens.TaskDetailScreen
import com.example.roomdatabasejetpack.screens.TaskListScreen
import com.example.roomdatabasejetpack.viewmodel.TaskViewModel

sealed class Screen(val route: String) {
    object TaskList : Screen("task_list")
    object AddEditTask : Screen("add_edit_task/{taskId}") {
        fun passArgs(taskId: Int = -1) = "add_edit_task/$taskId"
    }
    object TaskDetail : Screen("task_detail/{taskId}") {
        fun passArgs(taskId: Int) = "task_detail/$taskId"
    }
}

@Composable
fun TaskNavigation(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.TaskList.route
    ) {
        composable(Screen.TaskList.route) {
            TaskListScreen(
                viewModel = viewModel,
                onNavigateToAddTask = {
                    navController.navigate(Screen.AddEditTask.passArgs())
                },
                onNavigateToTaskDetail = { taskId ->
                    navController.navigate(Screen.TaskDetail.passArgs(taskId))
                }
            )
        }

        composable(
            route = Screen.AddEditTask.route,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            AddEditTaskScreen(
                viewModel = viewModel,
                taskId = taskId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.TaskDetail.route,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            TaskDetailScreen(
                viewModel = viewModel,
                taskId = taskId,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToEdit = {
                    navController.navigate(Screen.AddEditTask.passArgs(taskId))
                }
            )
        }
    }
}


