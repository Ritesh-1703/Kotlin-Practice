package com.example.k_mvvm_application

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.kmvvmapplication.Country
import com.example.kmvvmapplication.CountryViewModel
import kotlinx.coroutines.Job

class MainActivity : AppCompatActivity() {

    private lateinit var onClickListener: () -> Job
    private lateinit var viewModel: CountryViewModel
    private var sampleCountry = Country(name = "India")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)
            .get(CountryViewModel::class.java)

        viewModel.countries.observe(this) {
            findViewById<TextView>(R.id.txtData).text = it.joinToString {
                    c -> c.name
            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            viewModel.insert(Country(name = "India"))
        }

        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            viewModel.update(Country(id = 1, name = "Bharat"))
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            viewModel.delete(Country(id = 1, name = "Bharat"))
        }

        findViewById<Button>(R.id.btnDeleteAll).setOnClickListener {
            viewModel.deleteAll()
        }
    }
}
