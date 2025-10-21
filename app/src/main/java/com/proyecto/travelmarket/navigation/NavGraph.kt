package com.proyecto.travelmarket.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.proyecto.travelmarket.data.db.AppDatabase
import com.proyecto.travelmarket.data.repository.UserRepository
import com.proyecto.travelmarket.ui.screens.*
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModel
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModelFactory
import com.proyecto.travelmarket.ui.viewmodel.FavoritosViewModel

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
    
    // 🔹 Crear ViewModel de favoritos compartido
    val favoritosViewModel: FavoritosViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) { WelcomeScreen(navController) }

        // 🔹 Pasar el viewModel a las pantallas de Login y Registro
        composable(Screen.Login.route) { LoginScreen(navController, authViewModel) }
        composable(Screen.Register.route) { RegisterScreen(navController, authViewModel) }

        composable(Screen.Home.route) { HomeScreen(navController, favoritosViewModel, authViewModel) }
        composable(
            route = "detalle/{itemId}/{tipo}",
            arguments = listOf(
                navArgument("itemId") { type = NavType.IntType },
                navArgument("tipo") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
            val tipo = backStackEntry.arguments?.getString("tipo") ?: ""
            DetalleScreen(navController, itemId, tipo, favoritosViewModel)
        }
        composable(Screen.Perfil.route) { PerfilScreen(navController) }
        composable("lugares") { LugaresScreen(navController, favoritosViewModel, authViewModel) }
        composable("eventos") { EventosScreen(navController, favoritosViewModel, authViewModel) }
        composable("gastronomia") { GastronomiaScreen(navController, favoritosViewModel, authViewModel) }
        composable("transporte") { TransporteScreen(navController, favoritosViewModel, authViewModel) }
        composable("favoritos") { FavoritosScreen(navController, favoritosViewModel, authViewModel) }
    }
}
