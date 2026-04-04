package com.example.roomdatabasejetpack.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.roomdatabasejetpack.components.TaskCard
import com.example.roomdatabasejetpack.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onNavigateToAddTask: () -> Unit,
    onNavigateToTaskDetail: (Int) -> Unit
) {
    val tasks by viewModel.filteredTasks.collectAsState()
    val pendingCount by viewModel.pendingCount.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val showOnlyPending by viewModel.showOnlyPending.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Task Manager",
                        modifier = Modifier.animateContentSize()
                    )
                },
                actions = {
                    // Filter button with animation
                    IconButton(onClick = { viewModel.toggleFilter() }) {
                        Icon(
                            imageVector = if (showOnlyPending) Icons.Default.FilterAlt
                            else Icons.Default.FilterAltOff,
                            contentDescription = "Filter",
                            tint = if (showOnlyPending) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Delete all completed button
                    if (tasks.any { it.isCompleted }) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.deleteCompletedTasks()
                                    snackbarHostState.showSnackbar("Completed tasks deleted")
                                }
                            }
                        ) {
                            Icon(Icons.Default.DeleteSweep, "Delete All")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddTask,
                modifier = Modifier.size(56.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, "Add Task")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search tasks...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                            Icon(Icons.Default.Close, null)
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f)
                )
            )

            // Stats Bar
            AnimatedVisibility(
                visible = pendingCount > 0,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("📋 Pending Tasks", fontWeight = FontWeight.Bold)
                        Text("$pendingCount", fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Task List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = tasks,
                    key = { task -> task.id }
                ) { task ->
                    AnimatedContent(
                        targetState = task,
                        transitionSpec = {
                            fadeIn() with fadeOut() using SizeTransform(clip = true)
                        }
                    ) { currentTask ->
                        TaskCard(
                            task = currentTask,
                            onToggleComplete = { viewModel.toggleTaskCompletion(currentTask) },
                            onDelete = {
                                coroutineScope.launch {
                                    viewModel.deleteTask(currentTask)
                                    snackbarHostState.showSnackbar("Task deleted")
                                }
                            },
                            onClick = { onNavigateToTaskDetail(currentTask.id) }
                        )
                    }
                }

                // Empty state
                if (tasks.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    modifier = Modifier.size(64.dp),
                                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = if (searchQuery.isNotEmpty())
                                        "No matching tasks found"
                                    else "No tasks yet!\nTap + to add one",
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}