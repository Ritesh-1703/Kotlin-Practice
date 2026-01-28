package com.example.kotlin_student_application.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface StudentDao {

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student): Long

    // Read
    @Query("SELECT * FROM students ORDER BY name ASC")
    fun getStudents(): Flow<List<Student>>

    @Query("SELECT * FROM students WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchStudentsByName(searchQuery: String): Flow<List<Student>>

    @Query("SELECT * FROM students WHERE id = :id")
    fun getStudentById(id: Int): Student?

    @Query("SELECT * FROM students ORDER BY marks DESC")
    fun getStudentsByMarksDesc(): Flow<List<Student>>

    @Query("SELECT * FROM students ORDER BY marks ASC")
    fun getStudentsByMarksAsc(): Flow<List<Student>>

    // Update
    @Update
    suspend fun updateStudent(student: Student): Int

    @Query("UPDATE students SET marks = :newMarks WHERE id = :id")
    suspend fun updateStudentMarks(id: Int, newMarks: Int): Int

    // Delete
    @Delete
    suspend fun deleteStudent(student: Student): Int

    @Query("DELETE FROM students WHERE id = :studentId")
    suspend fun deleteStudentById(studentId: Int): Int

    @Query("DELETE FROM students")
    suspend fun deleteAllStudents(): Int

    // Stats
    @Query("SELECT COUNT(*) FROM students")
    fun getTotalStudentsCount(): Flow<Int>

    @Query("SELECT AVG(marks) FROM students")
    fun getAverageMarks(): Flow<Double>

    @Query("SELECT MAX(marks) FROM students")
    fun getMaximumMarks(): Flow<Int?>

    @Query("SELECT MIN(marks) FROM students")
    fun getMinimumMarks(): Flow<Int?>
}
