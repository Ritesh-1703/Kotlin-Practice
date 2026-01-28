package com.example.kotlin_student_application.data.repository

import com.example.kotlin_student_application.data.local.Student
import com.example.kotlin_student_application.data.local.StudentDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StudentRepository(private val studentDao : StudentDao) {

    suspend fun addStudent(name: String , studentclass : String, marks : Int): Long {
     val student = Student(
         name = name,
         studentclass = studentclass,
         marks = marks
     )
        return studentDao.insertStudent(student)
     }

    val  allStudents : Flow<List<Student>> = studentDao.getStudents()

    fun searchStudent(query: String): Flow<List<Student>> {
        return  if (query.isBlank()){
            studentDao.getStudents()
        }else {
            studentDao.searchStudentsByName(query)
        }
    }

    fun getStudntsSortedByMarks(acending: Boolean = true): Flow<List<Student>> {
        return if(acending){
            studentDao.getStudentsByMarksAsc()
        }else {
            studentDao.getStudentsByMarksDesc()
        }
    }

    suspend fun getStudentById(id: Int): Student? {
        return studentDao.getStudentById(id)
    }

// update

    suspend fun updateStudent(student: Student): Int{
        return  studentDao.updateStudent(student)
    }

    suspend fun updateStudentMarks(id: Int, newMarks: Int): Int {
        return studentDao.updateStudentMarks(id, newMarks)
    }

    //Delete

    suspend fun deleteStudent(student: Student): Int {
        return studentDao.deleteStudent(student)
    }

    suspend fun deleteStudentById(studentId: Int): Int {
        return studentDao.deleteStudentById(studentId)
    }

    suspend fun deleteAllStudents(): Int {
        return studentDao.deleteAllStudents()
    }

   // Statistics

    val totalStudentsCount: Flow<Int> = studentDao.getTotalStudentsCount()

    val averageMarks: Flow<Double> = studentDao.getAverageMarks()

    val maximumMarks: Flow<Int?> = studentDao.getMaximumMarks()

    val minimumMarks: Flow<Int?> = studentDao.getMinimumMarks()


   val studentStatistics: Flow<Map<String, Any>> = studentDao.getTotalStudentsCount()
       .map {
           count ->
           mapOf(
               "Total Students" to count,
               "Average Marks" to studentDao.getAverageMarks(),
               "Maximum Marks" to studentDao.getMaximumMarks(),
               "Minimum Marks" to studentDao.getMinimumMarks()
           )
       }

}


/**
 * REPOSITORY PATTERN KE IMPORTANT POINTS:
 * 1. Single Source of Truth - Data ka ek hi source
 * 2. Clean API provide karta hai ViewModel ko
 * 3. Data transformation yahan hota hai
 * 4. Network + Database combine kar sakte hain:
 *
 * Example: Network se data lo, phir database mein save karo
 * suspend fun refreshStudents() {
 *     val networkStudents = apiService.getStudents()
 *     studentDao.insertAll(networkStudents)
 * }
 */


