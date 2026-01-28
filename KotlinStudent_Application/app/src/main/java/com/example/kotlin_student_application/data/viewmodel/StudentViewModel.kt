package com.example.kotlin_student_application.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.kotlin_student_application.data.local.Student
import com.example.kotlin_student_application.data.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(private  val repository: StudentRepository) : ViewModel() {

    sealed class StudentState {
        data object Loading : StudentState()
        data class Success(val students: List<Student>) : StudentState()
        data class Error(val message: String) : StudentState()
        data object Empty : StudentState()

    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _sortOrder = MutableStateFlow(SortOrder.By_Name_ASC)
    val sortOrder: StateFlow<SortOrder> = _sortOrder.asStateFlow()

    enum class SortOrder {
        By_Name_ASC,
        By_Name_DESC,
        By_Marks_ASC,
        By_Marks_DESC
    }

    // ============ STUDENTS FLOW WITH FILTERS ============
    /**
     * Combined Flow - Search + Sort combine karega
     * Har ek change pe automatically new data emit hoga
     */
    val students: Flow<List<Student>> = combine(
        searchQuery,
        sortOrder
    ) { query, order ->
        Pair(query, order)
    }.flatMapLatest { (query, order) ->
        var flow = if (query.isBlank()) {
            repository.allStudents
        } else {
            repository.searchStudent(query)
        }

        flow = when (order) {
            SortOrder.By_Name_ASC -> flow.map { it.sortedBy { student -> student.name } }
            SortOrder.By_Name_DESC -> flow.map { it.sortedByDescending { student -> student.name } }
            StudentViewModel.SortOrder.By_Marks_ASC -> flow.map { it.sortedBy { student -> student.marks } }
            StudentViewModel.SortOrder.By_Marks_DESC -> flow.map { it.sortedByDescending { student -> student.marks } }

        }
        flow
    }

    // ============ UI STATE FLOW ============
    /**
     * UI State Flow - Loading, Success, Error handle karega
     */
    val uiState: Flow<StudentState> = students.map { students ->
        when {
            students.isEmpty() -> StudentState.Empty
            else -> StudentState.Success(students)
        }
    }
        .onStart { emit(StudentState.Loading) }
        .catch { e -> emit(StudentState.Error(e.message ?: "Unknow error")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = StudentState.Loading
        )

    val totalStudents: StateFlow<Int> = repository.allStudents
        .map { it.size } // 👈 convert List<Student> → Int
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )


    val averageMarks: Flow<Double> = repository.averageMarks
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )


    // ============ CRUD OPERATIONS ============

    fun addStudent(name: String, studentclass: String, marks: Int) {
        viewModelScope.launch {
            repository.addStudent(name, studentclass, marks)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            repository.deleteStudent(student)
        }

    }

    fun deleteStudentById(studentId: Int) {
        viewModelScope.launch {
            repository.deleteStudentById(studentId)
        }
    }

    // serarch & sort

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSortOrder(order: StudentViewModel.SortOrder) {
        _sortOrder.value = order
    }

    fun sortByName(acending: Boolean = true) {
        _sortOrder.value = if (acending) {
            StudentViewModel.SortOrder.By_Name_ASC
        } else {
            StudentViewModel.SortOrder.By_Name_DESC
        }
    }

    fun sortByMarks(acending: Boolean = true) {
        _sortOrder.value = if (acending) {
            StudentViewModel.SortOrder.By_Marks_ASC
        } else {
            StudentViewModel.SortOrder.By_Marks_DESC
        }
    }

    // Data Validation

    fun validateStudentData(
        name: String,
        studentclass: String,
        marks: String
    ): ValidationResult {
        return when{
            name.isBlank() -> ValidationResult.Error("Name cannot be empty")
            studentclass.isBlank() -> ValidationResult.Error("Class cannot be empty")
            marks.isBlank() -> ValidationResult.Error("Marks cannot be empty")
            !marks.matches(Regex("\\d+")) -> ValidationResult.Error("Marks must be number")
            marks.toInt()>100 -> ValidationResult.Error("Marks cannot be greater than 100")
            marks.toInt()<0 -> ValidationResult.Error("Marks cannot be less than 0")
            else -> ValidationResult.Success

        }

    }

    fun deleteAllStudent() {
        viewModelScope.launch {
            repository.deleteAllStudents()
        }
    }


    sealed class ValidationResult{
        data object Success: ValidationResult()
        data class Error(val message: String) :ValidationResult()



    }


}

