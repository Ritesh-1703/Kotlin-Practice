package com.example.kmvvmapplication2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cricketer::class], version = 1, exportSchema = false)
abstract class CricketerDatabase : RoomDatabase() {

    abstract fun cricketerDao(): CricketerDao

    companion object {
        @Volatile
        private var INSTANCE: CricketerDatabase? = null

        fun getDatabase(context: Context): CricketerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CricketerDatabase::class.java,
                    "cricketer_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}