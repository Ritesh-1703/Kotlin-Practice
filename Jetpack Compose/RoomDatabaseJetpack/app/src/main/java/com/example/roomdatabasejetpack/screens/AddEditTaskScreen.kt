package com.example.roomdatabasejetpack.screens

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.roomdatabasejetpack.components.formatDate
import com.example.roomdatabasejetpack.database.Priority
import com.example.roomdatabasejetpack.database.TaskEntity
import com.example.roomdatabasejetpack.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    viewModel: TaskViewModel,
    taskId: Int,
    onNavigateBack: () -> Unit
) {
    val selectedTask by viewModel.selectedTask.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Load task if editing
    LaunchedEffect(taskId) {
        if (taskId != -1) {
            viewModel.selectTask(
                TaskEntity(
                    id = taskId, title = "", description = "",
                    priority = Priority.MEDIUM, dueDate = System.currentTimeMillis()
                )
            )
            // In real app, fetch from DB
        }
    }

    var title by remember(taskId) { mutableStateOf("") }
    var description by remember(taskId) { mutableStateOf("") }
    var selectedPriority by remember(taskId) { mutableStateOf(Priority.MEDIUM) }
    var dueDate by remember(taskId) { mutableStateOf(System.currentTimeMillis()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }

    // Update fields when editing existing task
    LaunchedEffect(selectedTask) {
        if (taskId != -1 && selectedTask != null) {
            title = selectedTask!!.title
            description = selectedTask!!.description
            selectedPriority = selectedTask!!.priority
            dueDate = selectedTask!!.dueDate
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (taskId == -1) "Add Task" else "Edit Task") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                actions = {
                    // Save button with loading state
                    IconButton(
                        onClick = {
                            if (title.isNotBlank()) {
                                coroutineScope.launch {
                                    isSaving = true
                                    if (taskId == -1) {
                                        viewModel.addTask(title, description, selectedPriority, dueDate)
                                        snackbarHostState.showSnackbar("Task added successfully")
                                    } else {
                                        viewModel.updateTask(
                                            TaskEntity(
                                                id = taskId,
                                                title = title,
                                                description = description,
                                                priority = selectedPriority,
                                                dueDate = dueDate
                                            )
                                        )
                                        snackbarHostState.showSnackbar("Task updated successfully")
                                    }
                                    isSaving = false
                                    onNavigateBack()
                                }
                            }
                        },
                        enabled = title.isNotBlank() && !isSaving
                    ) {
                        if (isSaving) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        } else {
                            Icon(Icons.Default.Check, "Save")
                        }
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title Field
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Task Title *") },
                placeholder = { Text("Enter task title") },
                leadingIcon = { Icon(Icons.Default.Title, null) },
                singleLine = true
            )

            // Description Field
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Description") },
                placeholder = { Text("Enter task description (optional)") },
                leadingIcon = { Icon(Icons.Default.Description, null) },
                minLines = 3,
                maxLines = 5
            )

            // Priority Selection
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Priority",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Priority.values().forEach { priority ->
                            FilterChip(
                                selected = selectedPriority == priority,
                                onClick = { selectedPriority = priority },
                                label = { Text(priority.name) },
                                modifier = Modifier.weight(1f),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = priority.getColor().copy(alpha = 0.2f)
                                )
                            )
                        }
                    }
                }
            }

            // Due Date Selection
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDatePicker = true },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.DateRange, null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Due Date")
                    }
                    Text(
                        formatDate(dueDate),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Delete button for edit mode
            if (taskId != -1) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.deleteTask(selectedTask!!)
                            snackbarHostState.showSnackbar("Task deleted")
                            onNavigateBack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(Icons.Default.Delete, "Delete")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Delete Task")
                }
            }
        }
    }

    // Date Picker Dialog
    if (showDatePicker) {
        DatePickerDialog(
            onDateSelected = { date ->
                dueDate = date
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            datePickerState.selectedDateMillis?.let {
                                onDateSelected(it)
                            }
                        }
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}