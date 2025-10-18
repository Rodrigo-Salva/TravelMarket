package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyecto.travelmarket.ui.theme.*

@Composable
fun BottomNavigationBar(
    navController: NavController,
    selectedItem: Int
) {
    NavigationBar(
        containerColor = Blanco,
        modifier = Modifier.height(56.dp)
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Lugares"
                )
            },
            selected = selectedItem == 0,
            onClick = { 
                navController.navigate("lugares") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Rojo,
                unselectedIconColor = androidx.compose.ui.graphics.Color.Gray,
                indicatorColor = Blanco
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Eventos"
                )
            },
            selected = selectedItem == 1,
            onClick = { 
                navController.navigate("eventos") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Rojo,
                unselectedIconColor = androidx.compose.ui.graphics.Color.Gray,
                indicatorColor = Blanco
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio"
                )
            },
            selected = selectedItem == 2,
            onClick = { 
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Rojo,
                unselectedIconColor = androidx.compose.ui.graphics.Color.Gray,
                indicatorColor = Blanco
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Gastronomía"
                )
            },
            selected = selectedItem == 3,
            onClick = { 
                navController.navigate("gastronomia") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Rojo,
                unselectedIconColor = androidx.compose.ui.graphics.Color.Gray,
                indicatorColor = Blanco
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Transporte"
                )
            },
            selected = selectedItem == 4,
            onClick = { 
                navController.navigate("transporte") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Rojo,
                unselectedIconColor = androidx.compose.ui.graphics.Color.Gray,
                indicatorColor = Blanco
            )
        )
    }
}
