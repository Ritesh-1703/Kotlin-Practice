package com.example.kotlin_recyclerview_fragment_application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CountryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_country,container,false)

        val recyclerView =view.findViewById<RecyclerView>(R.id.countryReView)
        val back = view.findViewById<Button>(R.id.Back)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val list = listOf(
            Country("India","Mango"),
            Country("Australia","Cherry"),
            Country("England","Apple"),
            Country("New Zealand", "Grapes")
        )
        recyclerView.adapter = CountryAdapter(list)

        back.setOnClickListener {
           parentFragmentManager.popBackStack()

        }

        return  view
    }
}