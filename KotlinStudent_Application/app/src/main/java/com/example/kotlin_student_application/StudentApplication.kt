package com.example.kotlin_student_application

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlin_student_application.data.local.Student
import com.example.kotlin_student_application.data.local.StudentDatabase
import com.example.kotlin_student_application.data.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class StudentApplication : Application() {

    // Application scope for long-running tasks
    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    // Using by lazy - Initialize only when needed
    val database: StudentDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            StudentDatabase::class.java,
            "student_database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    applicationScope.launch{
                        // Pre-populate with sample data for testing
                        prePopulateDatabase()
                    }
                }
            })
            .build()
    }

    val studentRepository: StudentRepository by lazy {
        StudentRepository(database.studentDao())
    }

    // SharedPreferences for app settings
    val sharedPreferences by lazy {
        applicationContext.getSharedPreferences("student_app_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Initialize analytics, crash reporting, etc.
        setupAnalytics()
        setupCrashReporting()

        // Setup notification channels
        createNotificationChannels()
    }

    private suspend fun prePopulateDatabase() {
        // Add sample students for testing
        val sampleStudents = listOf(
            Student(name = "John Doe", studentclass  = "10th", marks = 85),
            Student(name = "Jane Smith", studentclass = "11th", marks = 92),
            Student(name = "Bob Johnson", studentclass = "12th", marks = 78),
            Student(name = "Alice Brown", studentclass = "10th", marks = 65),
            Student(name = "Charlie Wilson", studentclass = "9th", marks = 95)
        )

        sampleStudents.forEach { student ->
            studentRepository.addStudent(student.name, student.studentclass, student.marks)
        }
    }

    private fun setupAnalytics() {
        // Initialize Firebase or other analytics
    }

    private fun setupCrashReporting() {
        // Initialize Crashlytics or other crash reporting
    }

    private fun createNotificationChannels() {
        // Create notification channels for Android Oreo and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Create your notification channels here
        }
    }

    companion object {
        lateinit var instance: StudentApplication
            private set
    }
}

// Extension function to get application context easily
fun Context.getStudentApplication(): StudentApplication {
    return applicationContext as StudentApplication
}