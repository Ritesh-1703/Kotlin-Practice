package com.example.kotlin_student_application.data.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    requireContext().showToast(message)
}

// For formatting
fun Int.toGrade(): String {
    return when {
        this >= 90 -> "A+"
        this >= 80 -> "A"
        this >= 70 -> "B"
        this >= 60 -> "C"
        this >= 50 -> "D"
        else -> "F"
    }
}

fun Long.toFormattedDate(): String {
    return SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        .format(Date(this))
}