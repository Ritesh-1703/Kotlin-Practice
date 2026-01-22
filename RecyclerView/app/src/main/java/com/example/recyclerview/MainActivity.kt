package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerview.layoutManager= LinearLayoutManager(this)
        val list= listOf(
            Cricketer("Sachin Tendulkar", 18426),
            Cricketer("Virat Kohli", 12000),
            Cricketer("MS Dhoni", 10773),
            Cricketer("Rohit Sharma", 9800)
        )

        recyclerview.adapter = CricketerAddapter(list)
    }
}