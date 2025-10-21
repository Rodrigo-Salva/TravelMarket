package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.travelmarket.R
import com.proyecto.travelmarket.navigation.Screen
import com.proyecto.travelmarket.ui.theme.*

@Composable
fun WelcomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Rojo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // IMAGEN MÁS GRANDE
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = "Mascota Panamericanos",
                modifier = Modifier
                    .size(300.dp) // Aumentado de 180.dp a 280.dp
                    .padding(top = 40.dp, bottom = 12.dp)
            )

            Text(
                "TravelMarket",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Blanco,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp
                )
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                "Conoce lo mejor del Perú",
                color = Blanco,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp
                )
            )

            Text(
                "Juegos Panamericanos",
                color = Blanco,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate(Screen.Login.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blanco,
                    contentColor = Rojo
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Iniciar Sesión",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.navigate(Screen.Register.route) },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Blanco),
                border = BorderStroke(2.dp, Blanco),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Regístrate",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
