package com.example.kotlin_fragment_application

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val listView = view.findViewById<ListView>(R.id.listView)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        val cricketers = listOf(
            Cricketer("Sachin Tendulkar", 18426),
            Cricketer("Virat Kohli", 12000),
            Cricketer("MS Dhoni", 10773),
            Cricketer("Rohit Sharma", 9800)
        )

        listView.adapter = CricketerAdapter(requireContext(), cricketers)

        btnLogout.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        return view
    }
}
