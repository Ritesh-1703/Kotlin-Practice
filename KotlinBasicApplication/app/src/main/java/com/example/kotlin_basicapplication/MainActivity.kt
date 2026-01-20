package com.example.kotlin_basicapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.clickme)
        val enter =findViewById<Button>(R.id.enter)
        val text1= findViewById<EditText>(R.id.Text)
        btn.setOnClickListener {
            Toast.makeText(this,"Button Clicked Succesfully ", Toast.LENGTH_SHORT).show()
        }

        enter.setOnClickListener {
            Toast.makeText(this, "The entered text is:- ${text1.text}", Toast.LENGTH_LONG).show()
        }



        }

}


