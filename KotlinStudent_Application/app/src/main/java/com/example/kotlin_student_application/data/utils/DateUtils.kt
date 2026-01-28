package com.example.kotlin_student_application.data.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    private val dateTimeFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun formatDate(timestamp: Long): String {
        return dateFormat.format(Date(timestamp))
    }

    fun formatDateTime(timestamp: Long): String {
        return dateTimeFormat.format(Date(timestamp))
    }

    fun formatTime(timestamp: Long): String {
        return timeFormat.format(Date(timestamp))
    }

    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    fun isToday(timestamp: Long): Boolean {
        val today = Date()
        val date = Date(timestamp)
        return dateFormat.format(today) == dateFormat.format(date)
    }

    fun getRelativeTime(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val difference = now - timestamp

        return when {
            difference < 60000 -> "Just now" // Less than 1 minute
            difference < 3600000 -> "${difference / 60000} minutes ago" // Less than 1 hour
            difference < 86400000 -> "${difference / 3600000} hours ago" // Less than 1 day
            difference < 604800000 -> "${difference / 86400000} days ago" // Less than 1 week
            else -> formatDate(timestamp)
        }
    }
}