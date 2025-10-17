package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyecto.travelmarket.ui.theme.*

@Composable
fun DetalleScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BlancoDetalle
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Pantalla Detalle",
                style = MaterialTheme.typography.headlineMedium,
                color = Rojo
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Rojo,
                    contentColor = Blanco
                )
            ) {
                Text("Volver")
            }
        }
    }
}
