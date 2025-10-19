package com.proyecto.travelmarket.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.proyecto.travelmarket.data.db.AppDatabase
import com.proyecto.travelmarket.data.repository.UserRepository
import com.proyecto.travelmarket.ui.screens.*
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModel
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModelFactory

@Composable
fun NavGraph(navController: NavHostController) {

    // 🔹 Crear instancia de la base de datos y repositorio
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val repository = remember { UserRepository(db.userDao()) }

    // 🔹 Crear ViewModel con la factory
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(repository)
    )

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) { WelcomeScreen(navController) }

        // 🔹 Pasar el viewModel a las pantallas de Login y Registro
        composable(Screen.Login.route) { LoginScreen(navController, authViewModel) }
        composable(Screen.Register.route) { RegisterScreen(navController, authViewModel) }

        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Detalle.route) { DetalleScreen(navController) }
        composable(Screen.Perfil.route) { PerfilScreen(navController) }
        composable("lugares") { LugaresScreen(navController) }
        composable("eventos") { EventosScreen(navController) }
        composable("gastronomia") { GastronomiaScreen(navController) }
        composable("transporte") { TransporteScreen(navController) }
    }
}
