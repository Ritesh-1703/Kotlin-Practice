package com.example.kotlin_recyclerview_fragment_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val cricket= findViewById<Button>(R.id.Cricket)
        val county= findViewById<Button>(R.id.country)
        val logout = findViewById<Button>(R.id.logout)


        cricket.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CricketerFragment())
                .addToBackStack(null)
                .commit()
        }

        county.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CountryFragment())
                .addToBackStack(null)
                .commit()
        }

        logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
       }
    }
}