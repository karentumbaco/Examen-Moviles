package com.example.libro.navigation

sealed class AppScreens(val route: String){

    object Libros: AppScreens("Libros")
    object Reseña: AppScreens("Reseña/{libroId}")

}
