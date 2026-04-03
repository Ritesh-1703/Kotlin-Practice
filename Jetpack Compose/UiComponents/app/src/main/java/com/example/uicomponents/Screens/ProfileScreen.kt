package com.example.uicomponents.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.uicomponents.navigation.NavRoutes

@Composable
fun ProfileScreen(name: String?) {

    Text("Welcome $name")

}