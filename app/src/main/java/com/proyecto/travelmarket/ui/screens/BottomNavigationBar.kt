package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
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
        // 1. Ubicación / Lugares (resaltado en rojo cuando está seleccionado)
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
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
                unselectedIconColor = androidx.compose.ui.graphics.Color.Black,
                indicatorColor = Blanco
            )
        )
        
        // 2. Logros / Eventos (trofeo)
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.EmojiEvents,
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
                unselectedIconColor = androidx.compose.ui.graphics.Color.Black,
                indicatorColor = Blanco
            )
        )
        
        // 3. Inicio / Home
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
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
                unselectedIconColor = androidx.compose.ui.graphics.Color.Black,
                indicatorColor = Blanco
            )
        )
        
        // 4. Restaurante / Gastronomía
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Restaurant,
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
                unselectedIconColor = androidx.compose.ui.graphics.Color.Black,
                indicatorColor = Blanco
            )
        )
        
        // 5. Transporte / Movilidad (bus)
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.DirectionsBus,
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
                unselectedIconColor = androidx.compose.ui.graphics.Color.Black,
                indicatorColor = Blanco
            )
        )
    }
}
