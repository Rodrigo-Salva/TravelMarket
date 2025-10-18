package com.proyecto.travelmarket.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.proyecto.travelmarket.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) { WelcomeScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Register.route) { RegisterScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Detalle.route) { DetalleScreen(navController) }
        composable(Screen.Perfil.route) { PerfilScreen(navController) }
        composable("lugares") { LugaresScreen(navController) }
        composable("eventos") { EventosScreen(navController) }
        composable("gastronomia") { GastronomiaScreen(navController) }
        composable("transporte") { TransporteScreen(navController) }
    }
}
