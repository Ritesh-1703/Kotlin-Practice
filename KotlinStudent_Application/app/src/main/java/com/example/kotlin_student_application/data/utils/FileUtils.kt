package com.example.kotlin_student_application.data.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.kotlin_student_application.data.local.Student
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FileUtils {

    fun createCsvFile(context: Context, data: List<String>): File? {
        return try {
            // Create file name with timestamp
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "students_$timeStamp.csv"

            // Get downloads directory
            val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            val file = File(downloadsDir, fileName)

            // Write data to file
            FileOutputStream(file).use { outputStream ->
                data.forEach { line ->
                    outputStream.write("$line\n".toByteArray())
                }
            }

            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun readCsvFile(context: Context, uri: Uri): List<String> {
        return try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                inputStream.bufferedReader().readLines()
            } ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun exportStudentsToCsv(context: Context, students: List<Student>): File? {
        val csvData = mutableListOf<String>()

        // Add CSV header
        csvData.add("ID,Name,Class,Marks,Grade,Created Date")

        // Add student data
        students.forEach { student ->
            val grade = when {
                student.marks >= 90 -> "A+"
                student.marks >= 80 -> "A"
                student.marks >= 70 -> "B"
                student.marks >= 60 -> "C"
                student.marks >= 50 -> "D"
                else -> "F"
            }

            val date = DateUtils.formatDate(student.createat)

            csvData.add("${student.id},${student.name},${student.studentclass},${student.marks},$grade,$date")
        }

        return createCsvFile(context, csvData)
    }

    fun getAppStorageDirectory(context: Context): File {
        return context.getExternalFilesDir(null) ?: context.filesDir
    }

    fun getAvailableStorage(): Long {
        val path = Environment.getExternalStorageDirectory()
        val stat = android.os.StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val availableBlocks = stat.availableBlocksLong
        return availableBlocks * blockSize
    }
}