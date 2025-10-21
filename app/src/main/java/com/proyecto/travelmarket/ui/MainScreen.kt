package com.proyecto.travelmarket.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.proyecto.travelmarket.navigation.NavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}
