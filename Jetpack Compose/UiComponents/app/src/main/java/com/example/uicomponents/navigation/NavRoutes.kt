package com.example.uicomponents.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")

    object Profile : NavRoutes("profile/{name}"){
        fun createRoute(name : String) = "profile/$name"
    }

    object Auth : NavRoutes("auth")
    object Login: NavRoutes("login")
    object Signup: NavRoutes("signup")
}

