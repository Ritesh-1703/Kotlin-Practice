package com.example.ko_firebaseapplicationexample.utils

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("StaticFieldLeak")
object FirebaseUtils {
    val auth = FirebaseAuth.getInstance()
//    val database = FirebaseDatabase.getInstance().reference

    val database = FirebaseFirestore.getInstance()
}