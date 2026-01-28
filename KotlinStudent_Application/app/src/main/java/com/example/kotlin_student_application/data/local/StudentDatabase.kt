package com.example.kotlin_student_application.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.SQLiteConnection

@Database(
    entities = [Student :: class],
    version = 1,
    exportSchema = false   // Production mein true rakhenge
)
@TypeConverters() // Agar custom data types hain to
abstract class StudentDatabase: RoomDatabase() {

    // Abstract method for DAO - Room implementation provide karega
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var Instance: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_database"
                )
                    .addCallback(databaseCallback)
                    .build()
                Instance = instance
                instance

            }
        }

        private val databaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SQLiteConnection) {
                super.onCreate(db)
            }

            override fun onOpen(db: SQLiteConnection) {
                super.onOpen(db)
            }
        }
    }




}


/**
 * COMPANION OBJECT KYUN?
 * 1. Kotlin mein static methods ke liye
 * 2. Database instance globally accessible
 *
 * @Volatile KYUN?
 * 1. Thread safety ke liye
 * 2. Multiple threads same time pe instance na create karein
 *
 * synchronized KYUN?
 * 1. Ek time pe ek hi thread instance create kare
 * 2. Race condition se bachata hai
 */