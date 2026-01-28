package com.example.kotlin_student_application.data.utils

import java.util.regex.Pattern

object ValidationUtils {

    fun isValidName(name: String): Boolean {
        return name.isNotBlank() && name.length in 2..50
    }

    fun isValidClass(studentClass: String): Boolean {
        return studentClass.isNotBlank() && Pattern.matches("^[0-9]{1,2}(th|TH)\$", studentClass)
    }

    fun isValidMarks(marks: String): Boolean {
        return try {
            val marksInt = marks.toInt()
            marksInt in 0..100
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun getMarksGrade(marks: Int): Pair<String, String> {
        return when {
            marks >= 90 -> Pair("A+", "Excellent")
            marks >= 80 -> Pair("A", "Very Good")
            marks >= 70 -> Pair("B", "Good")
            marks >= 60 -> Pair("C", "Average")
            marks >= 50 -> Pair("D", "Below Average")
            else -> Pair("F", "Fail")
        }
    }

    fun validateStudentData(
        name: String,
        studentClass: String,
        marks: String
    ): ValidationResult {
        return when {
            !isValidName(name) -> ValidationResult.Error(
                field = "name",
                message = "Name must be 2-50 characters long"
            )
            !isValidClass(studentClass) -> ValidationResult.Error(
                field = "class",
                message = "Class must be in format like '10th', '11th'"
            )
            !isValidMarks(marks) -> ValidationResult.Error(
                field = "marks",
                message = "Marks must be a number between 0 and 100"
            )
            else -> ValidationResult.Success
        }
    }

    sealed class ValidationResult {
        data object Success : ValidationResult()
        data class Error(val field: String, val message: String) : ValidationResult()
    }
}