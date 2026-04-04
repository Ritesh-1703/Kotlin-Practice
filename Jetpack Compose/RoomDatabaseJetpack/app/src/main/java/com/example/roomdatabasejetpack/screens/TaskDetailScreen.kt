package com.example.roomdatabasejetpack.screens
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.roomdatabasejetpack.components.formatDate
import com.example.roomdatabasejetpack.database.TaskEntity
import com.example.roomdatabasejetpack.viewmodel.TaskViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TaskDetailScreen(
    viewModel: TaskViewModel,
    taskId: Int,
    onNavigateBack: () -> Unit,
    onNavigateToEdit: () -> Unit
) {
    val selectedTask by viewModel.getTaskById(taskId)
        .collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToEdit) {
                        Icon(Icons.Default.Edit, "Edit")
                    }
                }
            )
        }
    ) { paddingValues ->
        selectedTask?.let { task ->
            AnimatedContent(
                targetState = task,
                transitionSpec = {
                    fadeIn() with fadeOut() using SizeTransform(clip = true)
                }
            ) { currentTask ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Priority Badge
                    Surface(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        shape = MaterialTheme.shapes.small,
                        color = currentTask.priority.getColor().copy(alpha = 0.2f),
                        contentColor = currentTask.priority.getColor()
                    ) {
                        Text(
                            text = currentTask.priority.name,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    // Title
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Title",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                currentTask.title,
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    }

                    // Description
                    if (currentTask.description.isNotEmpty()) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            )
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    "Description",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    currentTask.description,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }

                    // Due Date
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.DateRange, null, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Due Date")
                            }
                            Text(
                                formatDate(currentTask.dueDate),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    // Status
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Status")
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (currentTask.isCompleted) {
                                    Icon(Icons.Default.CheckCircle, null,
                                        tint = Color.Green, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Completed", color = Color.Green)
                                } else {
                                    Icon(Icons.Default.Pending, null,
                                        tint = Color.Yellow, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Pending", color = Color.Green)
                                }
                            }
                        }
                    }

                    // Created Date
                    Text(
                        "Created: ${formatDate(currentTask.createdAt)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}