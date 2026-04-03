package com.example.uicomponents.Screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun SignupScreen(navController: NavController) {

    Column {
        Text("Signup Screen")

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Back")
        }
    }
}