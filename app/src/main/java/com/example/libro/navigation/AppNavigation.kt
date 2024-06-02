package com.example.libro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.libro.screens.LibrosScreen
import com.example.libro.screens.ReseñaScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController =navController , startDestination = AppScreens.Libros.route){
        composable(route = AppScreens.Libros.route){
            LibrosScreen(navController)
        }
        composable(route = AppScreens.Reseña.route + "/{libroId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val libroId = arguments.getString("libroId")
            ReseñaScreen(navController, libroId)
        }


    }

}