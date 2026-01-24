package com.example.kmvvmapplication2.data.Repository

import com.example.kmvvmapplication2.data.local.Cricketer
import com.example.kmvvmapplication2.data.local.CricketerDao

class CricketerRepository(private val dao: CricketerDao) {

    val allCricketers = dao.getAllCricketers()

    suspend fun insert(cricketer: Cricketer) {
        dao.insert(cricketer)
    }

    suspend fun update(cricketer: Cricketer) {
        dao.update(cricketer)
    }

    suspend fun delete(cricketer: Cricketer) {
        dao.delete(cricketer)
    }
}