package com.example.uicomponents.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.uicomponents.navigation.NavRoutes

@Composable
fun HomeScreen(navController: NavController) {

    Column {

        Text("Home Screen")

        Button(onClick = {
            navController.navigate(
                NavRoutes.Profile.createRoute("Ritesh")
            )
        }) {
            Text("Go to Profile")
        }

        Button(onClick = {
            navController.navigate(NavRoutes.Auth.route)
        }) {
            Text("Go to Auth Flow")
        }
    }
}