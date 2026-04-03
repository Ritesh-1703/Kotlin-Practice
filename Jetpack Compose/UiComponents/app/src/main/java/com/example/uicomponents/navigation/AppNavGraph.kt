package com.example.uicomponents.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.uicomponents.Screens.HomeScreen
import com.example.uicomponents.Screens.ProfileScreen
import com.example.uicomponents.Screens.auth.LoginScreen
import com.example.uicomponents.Screens.auth.SignupScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {

        // 🔹 HOME
        composable(NavRoutes.Home.route) {
            HomeScreen(navController)
        }

        // 🔹 PROFILE WITH ARGUMENT + DEEP LINK
        composable(
            route = NavRoutes.Profile.route,
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
            }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://example.com/profile/{name}"
            })
        ) { backStackEntry ->

            val name = backStackEntry.arguments?.getString("name")
            ProfileScreen(name)
        }

        // 🔹 NESTED NAVIGATION (AUTH FLOW)
        navigation(
            startDestination = NavRoutes.Login.route,
            route = NavRoutes.Auth.route
        ) {
            composable(NavRoutes.Login.route) {
                LoginScreen(navController)
            }

            composable(NavRoutes.Signup.route) {
                SignupScreen(navController)
            }
        }
    }
}
