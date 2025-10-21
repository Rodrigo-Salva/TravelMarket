package com.proyecto.travelmarket.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Detalle : Screen("detalle")
    object Perfil : Screen("perfil")
}


