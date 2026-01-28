package com.example.kotlin_student_application.data.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_student_application.R
import com.example.kotlin_student_application.StudentApplication
import com.example.kotlin_student_application.data.local.Student
import com.example.kotlin_student_application.data.viewmodel.StudentViewModel
import com.example.kotlin_student_application.data.viewmodel.StudentViewModelFactory
import com.example.kotlin_student_application.databinding.ActivityMainBinding
import com.example.kotlin_student_application.databinding.DialogAddStudentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter

    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(
            (application as StudentApplication).studentRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupClickListeners()
        setupObservers()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupRecyclerView() {
        studentAdapter = StudentAdapter(
            onItemClick = { showStudentDetails(it) },
            onDeleteClick = { showDeleteConfirmation(it) },
            onEditClick = { showEditDialog(it) }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            showAddStudentDialog()
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    when (state) {
                        is StudentViewModel.StudentState.Loading -> {
                            showLoading(true)
                        }

                        is StudentViewModel.StudentState.Success -> {
                            showLoading(false)
                            studentAdapter.submitList(state.students)
                        }

                        is StudentViewModel.StudentState.Error -> {
                            showLoading(false)
                            Toast.makeText(
                                this@MainActivity,
                                state.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is StudentViewModel.StudentState.Empty -> {
                            showLoading(false)
                            studentAdapter.submitList(emptyList())
                            showEmptyState(true)
                        }
                    }
                }
            }
        }
    }

    // ================= DIALOGS =================

    private fun showAddStudentDialog() {
        val dialogBinding = DialogAddStudentBinding.inflate(layoutInflater)

        AlertDialog.Builder(this)
            .setTitle("Add Student")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                val name = dialogBinding.etName.text.toString()
                val studentClass = dialogBinding.etClass.text.toString()
                val marks = dialogBinding.etMarks.text.toString()

                when (val result =
                    viewModel.validateStudentData(name, studentClass, marks)) {

                    is StudentViewModel.ValidationResult.Success -> {
                        viewModel.addStudent(name, studentClass, marks.toInt())
                        Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show()
                    }

                    is StudentViewModel.ValidationResult.Error -> {
                        Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditDialog(student: Student) {
        val dialogBinding = DialogAddStudentBinding.inflate(layoutInflater)

        dialogBinding.apply {
            etName.setText(student.name)
            etClass.setText(student.studentclass)
            etMarks.setText(student.marks.toString())
            tilTitle.text = "Edit Student"
        }

        AlertDialog.Builder(this)
            .setTitle("Edit Student")
            .setView(dialogBinding.root)
            .setPositiveButton("Update") { _, _ ->
                val name = dialogBinding.etName.text.toString()
                val studentClass = dialogBinding.etClass.text.toString()
                val marks = dialogBinding.etMarks.text.toString()

                when (val result =
                    viewModel.validateStudentData(name, studentClass, marks)) {

                    is StudentViewModel.ValidationResult.Success -> {
                        val updatedStudent = student.copy(
                            name = name,
                            studentclass =  studentClass,
                            marks = marks.toInt()
                        )
                        viewModel.updateStudent(updatedStudent)
                        Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
                    }

                    is StudentViewModel.ValidationResult.Error -> {
                        Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDeleteConfirmation(student: Student) {
        AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Delete ${student.name}?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteStudent(student)
                Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showStudentDetails(student: Student) {
        AlertDialog.Builder(this)
            .setTitle("Student Details")
            .setMessage(
                """
                ID: ${student.id}
                Name: ${student.name}
                Class: ${student.studentclass}
                Marks: ${student.marks}
                Added: ${formatDate(student.createat)}
                """.trimIndent()
            )
            .setPositiveButton("OK", null)
            .show()
    }

    // ================= MENU =================

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText ?: "")
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_name_asc -> {
                viewModel.sortByName(true); true
            }
            R.id.action_sort_name_desc -> {
                viewModel.sortByName(false); true
            }
            R.id.action_sort_marks_asc -> {
                viewModel.sortByMarks(true); true
            }
            R.id.action_sort_marks_desc -> {
                viewModel.sortByMarks(false); true
            }
            R.id.action_delete_all -> {
                showDeleteAllConfirmation(); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteAllConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Delete All")
            .setMessage("Delete all students?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteAllStudent()
                Toast.makeText(this, "All students deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // ================= HELPERS =================

    private fun showLoading(show: Boolean) {
        // optional progress bar
    }

    private fun showEmptyState(show: Boolean) {
        // optional empty state
    }

    private fun formatDate(timestamp: Long): String {
        return android.text.format.DateFormat
            .getDateFormat(this)
            .format(Date(timestamp))
    }
}
