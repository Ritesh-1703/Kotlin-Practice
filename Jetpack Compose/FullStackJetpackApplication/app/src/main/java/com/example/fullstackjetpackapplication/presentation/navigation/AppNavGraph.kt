package com.example.fullstackjetpackapplication.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.fullstackjetpackapplication.presentation.screens.home.HomeScreen
import com.example.fullstackjetpackapplication.presentation.screens.login.LoginScreen
import com.example.fullstackjetpackapplication.presentation.screens.register.RegisterScreen
import com.example.fullstackjetpackapplication.presentation.screens.splash.SplashScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}