package com.example.kotlin_basicapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        Toast.makeText(this, "Welcome to First Android App", Toast.LENGTH_LONG).show()


        val listLayout = findViewById<LinearLayout>(R.id.listLayout)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Simple list (no RecyclerView)
        val items = arrayOf(
            "Apple - India",
            "Banana - Brazil",
            "Orange - USA",
            "Mango - India",
            "Grapes - France"
        )

        for (item in items) {
            val textView = TextView(this)
            textView.text = item
            textView.textSize = 18f
            textView.setTextColor(resources.getColor(R.color.textPrimary))
            textView.setBackgroundColor(resources.getColor(R.color.white))
            textView.setPadding(20, 20, 20, 20)

            listLayout.addView(textView)

        }


        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}