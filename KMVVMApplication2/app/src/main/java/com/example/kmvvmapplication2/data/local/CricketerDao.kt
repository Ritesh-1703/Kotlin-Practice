package com.example.kmvvmapplication2.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CricketerDao {

    @Insert
    suspend fun insert(cricketer: Cricketer)

    @Update
    suspend fun update(cricketer: Cricketer)

    @Delete
    suspend fun delete(cricketer: Cricketer)

    @Query("SELECT * FROM cricketer_table ORDER BY id DESC")
    fun getAllCricketers(): LiveData<List<Cricketer>>
}