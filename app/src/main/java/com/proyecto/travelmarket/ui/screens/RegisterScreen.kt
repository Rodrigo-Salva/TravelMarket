package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.travelmarket.navigation.Screen
import com.proyecto.travelmarket.ui.theme.* @Composable
fun RegisterScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Rojo) // El fondo de la pantalla completa es ROJO
    ) {
        // 1. SECCIÓN ROJA SUPERIOR con elementos (Ajuste de Centrado y Flecha)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                // Altura para centrar el nombre y dar espacio a la flecha
                .height(150.dp)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center // Centrado global
        ) {
            // *** BOTÓN DE RETROCESO (Círculo Blanco con Flecha Negra) ***
            Surface(
                shape = CircleShape,
                color = Blanco,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopStart)
                    // Mismos ajustes de offset que la pantalla de Login para bajar la flecha.
                    .offset(x = (-8).dp, y = 30.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Logo TravelMarket (Centrado en el área roja)
            Text(
                "TravelMarket",
                color = Blanco,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 36.sp
                ),
                // Alignment.Center aquí asegura que el nombre esté al medio del Box
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // 2. SECCIÓN BLANCA DEL FORMULARIO (Tarjeta superpuesta)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                // Se mantiene la altura de 0.8f para acomodar los 4 campos y el botón
                .fillMaxHeight(0.8f),
            color = Blanco,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Texto descriptivo
                Text(
                    "Lima - Juegos Panamericanos",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Título de Bienvenida
                Text(
                    "Bienvenido",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // CAMPO NOMBRE COMPLETO
                Text("Nombre completo:", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Gris,
                        unfocusedContainerColor = Gris,
                        disabledContainerColor = Gris,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(50)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // CAMPO USUARIO
                Text("Usuario:", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Gris,
                        unfocusedContainerColor = Gris,
                        disabledContainerColor = Gris,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(50)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // CAMPO CONTRASEÑA
                Text("Contraseña:", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Gris,
                        unfocusedContainerColor = Gris,
                        disabledContainerColor = Gris,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(50)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // CAMPO CONFIRMA CONTRASEÑA
                Text("Confirma tu contraseña:", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal))
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = confirm,
                    onValueChange = { confirm = it },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Gris,
                        unfocusedContainerColor = Gris,
                        disabledContainerColor = Gris,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(50)
                )

                Spacer(modifier = Modifier.height(30.dp))

                // BOTÓN REGÍSTRATE
                Button(
                    onClick = { navController.navigate(Screen.Home.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Rojo, contentColor = Blanco),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        "Regístrate",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ENLACE INICIA SESIÓN
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "¿Ya tienes cuenta? ",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        "Iniciar Sesión",
                        color = Rojo,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { navController.navigate(Screen.Login.route) }
                    )
                }
            }
        }
    }
}