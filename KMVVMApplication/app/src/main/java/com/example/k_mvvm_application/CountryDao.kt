package com.example.kmvvmapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CountryDao {

    // CREATE
    @Insert
    suspend fun insert(country: Country)

    // READ
    @Query("SELECT * FROM country_table")
    fun getAllCountries(): LiveData<List<Country>>

    // UPDATE
    @Update
    suspend fun update(country: Country)

    // DELETE single
    @Delete
    suspend fun delete(country: Country)

    // DELETE all
    @Query("DELETE FROM country_table")
    suspend fun deleteAll()
}
