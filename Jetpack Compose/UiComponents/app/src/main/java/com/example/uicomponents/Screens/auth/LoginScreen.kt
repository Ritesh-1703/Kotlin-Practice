package com.example.uicomponents.Screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.uicomponents.navigation.NavRoutes

@Composable
fun LoginScreen(navController: NavController) {

    Column {

        Text("Login Screen")

        Button(onClick = {
            navController.navigate(NavRoutes.Signup.route)
        }) {
            Text("Go to Signup")
        }
    }
}