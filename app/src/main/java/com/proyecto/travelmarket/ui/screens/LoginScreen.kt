package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proyecto.travelmarket.navigation.Screen
import com.proyecto.travelmarket.ui.theme.Blanco
import com.proyecto.travelmarket.ui.theme.Rojo
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModel
import com.proyecto.travelmarket.ui.viewmodel.LoginResult
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Estados para mostrar errores individuales
    var userError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var generalError by remember { mutableStateOf("") }
    var showLoadingDialog by remember { mutableStateOf(false) }

    val loginState by viewModel.loginState.collectAsState()
    val scrollState = rememberScrollState()

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
                    .verticalScroll(scrollState)
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
                    onValueChange = {
                        user = it
                        userError = false
                        generalError = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Gris,
                        unfocusedContainerColor = Gris,
                        disabledContainerColor = Gris,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    shape = RoundedCornerShape(50),
                    singleLine = true
                )
                if (userError) {
                    Text(
                        text = "Debe llenar el campo de usuario",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // CAMPO CONTRASEÑA
                Text("Contraseña:", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                        generalError = ""
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

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
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    shape = RoundedCornerShape(50),
                    singleLine = true
                )
                if (passwordError) {
                    Text(
                        text = "Debe llenar el campo de contraseña",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                // Mostrar mensaje de error general
                if (generalError.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = generalError,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // BOTÓN LOGIN
                Button(
                    onClick = {
                        userError = false
                        passwordError = false
                        generalError = ""

                        when {
                            user.isEmpty() && password.isEmpty() -> {
                                userError = true
                                passwordError = true
                            }
                            user.isEmpty() -> {
                                userError = true
                            }
                            password.isEmpty() -> {
                                passwordError = true
                            }
                            else -> {
                                showLoadingDialog = true
                                viewModel.login(user, password)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Rojo, contentColor = Blanco),
                    shape = RoundedCornerShape(50),
                    enabled = !showLoadingDialog
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
            }
        }
    }

    // Dialog de "Iniciando sesión"
    if (showLoadingDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(
                    "Iniciando sesión",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = Rojo
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Por favor espera...")
                }
            },
            confirmButton = { }
        )
    }

    // Manejo del estado de login
    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginResult.Success -> {
                delay(1500)
                showLoadingDialog = false
                viewModel.resetLoginState()
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
            is LoginResult.UserNotFound -> {
                showLoadingDialog = false
                generalError = "La cuenta no existe. Por favor regístrate primero"
                viewModel.resetLoginState()
            }
            is LoginResult.WrongPassword -> {
                showLoadingDialog = false
                generalError = "Contraseña incorrecta"
                viewModel.resetLoginState()
            }
            is LoginResult.Idle -> { }
        }
    }
}
