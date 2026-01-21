package com.example.kotlin_fragment_application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CricketerAdapter(
    context: Context,
    private val list: List<Cricketer>
) : ArrayAdapter<Cricketer>(context, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.row_cricketer, parent, false)

        view.findViewById<TextView>(R.id.txtName).text = list[position].name
        view.findViewById<TextView>(R.id.txtRuns).text =
            "Runs: ${list[position].runs}"

        return view
    }
}
