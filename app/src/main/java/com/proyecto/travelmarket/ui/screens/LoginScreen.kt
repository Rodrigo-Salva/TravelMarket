package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proyecto.travelmarket.navigation.Screen
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // Estado para controlar la visibilidad (el "ojito")
    var passwordVisible by remember { mutableStateOf(false) }

    val loginState by viewModel.loginState.collectAsState()

    val Rojo = Color(0xFFC62828)
    val Blanco = Color.White
    val Gris = Color(0xFFEEEEEE)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Rojo)
    ) {
        // SECCIÓN ROJA SUPERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            // BOTÓN ATRÁS
            Surface(
                shape = CircleShape,
                color = Blanco,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopStart)
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

            // LOGO
            Text(
                "TravelMarket",
                color = Blanco,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 36.sp
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // SECCIÓN BLANCA INFERIOR
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
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
                Text(
                    "Lima - Juegos Panamericanos",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    "Bienvenido",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    ),
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // CAMPO USUARIO
                Text("Usuario:", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = user,
                    onValueChange = { user = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Gris,
                        unfocusedContainerColor = Gris,
                        disabledContainerColor = Gris,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(50),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                // CAMPO CONTRASEÑA (IMPLEMENTACIÓN DEL OJITO)
                Text("Contraseña:", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },

                    // 1. Alterna la transformación visual
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                    // 2. Añade el ícono interactivo
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility // Ícono de ojo abierto
                        else Icons.Filled.VisibilityOff // Ícono de ojo tachado

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, contentDescription = if (passwordVisible) "Ocultar Contraseña" else "Mostrar Contraseña")
                        }
                    },

                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Gris,
                        unfocusedContainerColor = Gris,
                        disabledContainerColor = Gris,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(50),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(40.dp))

                // BOTÓN LOGIN
                Button(
                    onClick = { viewModel.login(user, password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Rojo, contentColor = Blanco),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Iniciar Sesión", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ENLACE REGISTRO
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("¿No tienes cuenta? ", color = Color.Black, fontSize = 14.sp)
                    Text(
                        "Regístrate",
                        color = Rojo,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.Register.route)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Mostrar feedback de login
                when (loginState) {
                    true -> {
                        LaunchedEffect(Unit) {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    }
                    false -> {
                        Text(
                            "Usuario o contraseña incorrectos",
                            color = Color.Red,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                    null -> {}
                }
            }
        }
    }
}