package com.example.kotlin_recyclerview_fragment_application


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CricketerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cricketer,container, false)

        val  recyclerView = view.findViewById<RecyclerView>(R.id.cricketReView)
        val btn =view.findViewById<Button>(R.id.Back2)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val list = listOf(
            Cricketer("Sachin Tendulkar", 18426),
            Cricketer("Virat Kohli", 12000),
            Cricketer("MS Dhoni", 10773),
            Cricketer("Rohit Sharma", 9800)
        )

        recyclerView.adapter = CricketerAdapter(list)

            btn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return  view
    }
}