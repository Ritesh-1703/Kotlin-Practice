package com.example.kmvvmapplication2.data.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kmvvmapplication2.R
import com.example.kmvvmapplication2.data.local.Cricketer

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CricketerViewModel
    private lateinit var adapter: CricketerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CricketerViewModel::class.java]

        adapter = CricketerAdapter(
            onDelete = {
                viewModel.delete(it)
            },
            onUpdate = {
                showUpdateDialog(it)
            }
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allCricketers.observe(this) {
            adapter.setData(it)
        }

        val etName = findViewById<EditText>(R.id.etName)
        val etRuns = findViewById<EditText>(R.id.etRuns)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val runs = etRuns.text.toString().toIntOrNull() ?: 0

            if (name.isNotEmpty()) {
                viewModel.insert(Cricketer(name = name, runs = runs))
                etName.text.clear()
                etRuns.text.clear()
            }
        }
    }

    @SuppressLint("CutPasteId")
    private fun showUpdateDialog(cricketer: Cricketer) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_update_cricketer, null)
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val etName = dialogView.findViewById<EditText>(R.id.etUpdateName)
        val etRuns = dialogView.findViewById<EditText>(R.id.etUpdateRuns)

        etName.setText(cricketer.name)
        etRuns.setText(cricketer.runs.toString())

        val btnUpdate = dialogView.findViewById<Button>(R.id.btnUpdate)
        btnUpdate.setOnClickListener {
            val newName = etName.text.toString()
            val newRuns = etRuns.text.toString().toIntOrNull() ?: 0
            if (newName.isNotEmpty()) {

                val updatedCricketer = Cricketer(
                    id = cricketer.id,   // ⭐ MOST IMPORTANT
                    name = newName,
                    runs = newRuns
                )

                viewModel.update(updatedCricketer)
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}