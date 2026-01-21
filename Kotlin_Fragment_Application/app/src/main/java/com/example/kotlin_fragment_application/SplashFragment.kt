package com.example.kotlin_fragment_application

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }, 2000)

        return view
    }
}
