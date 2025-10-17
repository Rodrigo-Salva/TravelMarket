package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyecto.travelmarket.navigation.Screen
import com.proyecto.travelmarket.ui.theme.*

@Composable
fun PerfilScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Blanco
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Pantalla Perfil",
                style = MaterialTheme.typography.headlineMedium,
                color = Rojo
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate(Screen.Home.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gris,
                    contentColor = Color.Black
                )
            ) {
                Text("Volver al Home")
            }
        }
    }
}
