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
import androidx.compose.material.icons.filled.CheckCircle
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

@Composable
fun RegisterScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    var nombre by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }

    var nombreError by remember { mutableStateOf(false) }
    var usuarioError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmError by remember { mutableStateOf(false) }
    var passwordMismatchError by remember { mutableStateOf(false) }
    var passwordLengthError by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val Gris = Color(0xFFEEEEEE)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Rojo)
    ) {
        // SECCIÓN ROJA
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
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

        // SECCIÓN BLANCA
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
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // CAMPO NOMBRE
                Text("Nombre completo:")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = nombre,
                    onValueChange = {
                        nombre = it
                        nombreError = false
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
                if (nombreError) {
                    Text(
                        text = "Debe llenar el campo de nombre",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // CAMPO USUARIO
                Text("Usuario:")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = usuario,
                    onValueChange = {
                        usuario = it
                        usuarioError = false
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
                if (usuarioError) {
                    Text(
                        text = "Debe llenar el campo de usuario",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // CAMPO CONTRASEÑA
                Text("Contraseña:")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                        passwordLengthError = false
                        passwordMismatchError = false
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
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
                if (passwordLengthError) {
                    Text(
                        text = "La contraseña debe tener al menos 6 caracteres",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // CAMPO CONFIRMA CONTRASEÑA
                Text("Confirma tu contraseña:")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = confirm,
                    onValueChange = {
                        confirm = it
                        confirmError = false
                        passwordMismatchError = false
                    },
                    visualTransformation = if (confirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (confirmVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = { confirmVisible = !confirmVisible }) {
                            Icon(imageVector = image, contentDescription = if (confirmVisible) "Ocultar Contraseña" else "Mostrar Contraseña")
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
                if (confirmError) {
                    Text(
                        text = "Debe confirmar su contraseña",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
                if (passwordMismatchError) {
                    Text(
                        text = "Las contraseñas no coinciden",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                // BOTÓN REGISTRO
                Button(
                    onClick = {
                        nombreError = false
                        usuarioError = false
                        passwordError = false
                        confirmError = false
                        passwordMismatchError = false
                        passwordLengthError = false

                        var hasError = false

                        if (nombre.isEmpty()) {
                            nombreError = true
                            hasError = true
                        }
                        if (usuario.isEmpty()) {
                            usuarioError = true
                            hasError = true
                        }
                        if (password.isEmpty()) {
                            passwordError = true
                            hasError = true
                        }
                        if (confirm.isEmpty()) {
                            confirmError = true
                            hasError = true
                        }

                        if (!hasError) {
                            when {
                                password.length < 6 -> {
                                    passwordLengthError = true
                                }
                                password != confirm -> {
                                    passwordMismatchError = true
                                }
                                else -> {
                                    viewModel.register(nombre, usuario, password)
                                    showSuccessDialog = true
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Rojo, contentColor = Blanco),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Regístrate", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ENLACE LOGIN
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("¿Ya tienes cuenta? ", color = Color.Black, fontSize = 14.sp)
                    Text(
                        "Iniciar Sesión",
                        color = Rojo,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.Login.route)
                        }
                    )
                }
            }
        }
    }

    // Dialog de registro exitoso
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { },
            icon = {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = "Éxito",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(64.dp)
                )
            },
            title = {
                Text(
                    "¡Registro exitoso!",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            text = {
                Text(
                    "Tu cuenta ha sido creada correctamente. Ahora puedes iniciar sesión.",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Rojo)
                ) {
                    Text("Ir al Login", color = Blanco)
                }
            },
            containerColor = Blanco,
            shape = RoundedCornerShape(16.dp)
        )
    }
}
