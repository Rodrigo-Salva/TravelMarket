package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proyecto.travelmarket.data.DataSource
import com.proyecto.travelmarket.ui.screens.components.ItemCard
import com.proyecto.travelmarket.ui.screens.components.AccountDrawerContent
import com.proyecto.travelmarket.R
import com.proyecto.travelmarket.ui.theme.Rojo
import com.proyecto.travelmarket.ui.viewmodel.FavoritosViewModel
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModel
import com.proyecto.travelmarket.utils.ImageUtils

enum class OrdenTransporte {
    RATING_DESC,
    PRECIO_ASC,
    PRECIO_DESC,
    NOMBRE_ASC
}

@Composable
fun TransporteScreen(
    navController: NavController,
    favoritosViewModel: FavoritosViewModel = viewModel(),
    authViewModel: AuthViewModel
) {
    var ordenSeleccionado by remember { mutableStateOf(OrdenTransporte.RATING_DESC) }
    var menuExpanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentUser by authViewModel.currentUser.collectAsState()
    
    val transportesOrdenados = remember(ordenSeleccionado) {
        when (ordenSeleccionado) {
            OrdenTransporte.RATING_DESC -> DataSource.transportes.sortedByDescending { it.rating }
            OrdenTransporte.PRECIO_ASC -> DataSource.transportes.sortedBy { it.precio }
            OrdenTransporte.PRECIO_DESC -> DataSource.transportes.sortedByDescending { it.precio }
            OrdenTransporte.NOMBRE_ASC -> DataSource.transportes.sortedBy { it.nombre }
        }
    }
    
    val transportesFiltrados = remember(transportesOrdenados, searchText) {
        if (searchText.isNotEmpty()) {
            transportesOrdenados.filter { transporte ->
                transporte.nombre.contains(searchText, ignoreCase = true) ||
                transporte.descripcion.contains(searchText, ignoreCase = true) ||
                transporte.tipo.contains(searchText, ignoreCase = true)
            }
        } else {
            transportesOrdenados
        }
    }
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { 
            AccountDrawerContent(
                currentUser = currentUser,
                navController = navController,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("transporte") { inclusive = true }
                    }
                },
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Rojo)
                    .padding(16.dp)
            ) {
                // Header con título y menú
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Transporte",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    IconButton(onClick = { 
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Menú de cuenta",
                            tint = Color.White
                        )
                    }
                }

                Text(
                    text = "Movilidad en Lima",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Barra de búsqueda
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    placeholder = { Text("Buscar transporte") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectedItem = 4
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Selector de orden
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF5F5F5),
                shadowElevation = 2.dp
            ) {
                Box {
                    OutlinedButton(
                        onClick = { menuExpanded = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = when (ordenSeleccionado) {
                                    OrdenTransporte.RATING_DESC -> "Ordenar por: Rating"
                                    OrdenTransporte.PRECIO_ASC -> "Ordenar por: Precio (Menor a Mayor)"
                                    OrdenTransporte.PRECIO_DESC -> "Ordenar por: Precio (Mayor a Menor)"
                                    OrdenTransporte.NOMBRE_ASC -> "Ordenar por: Nombre (A-Z)"
                                },
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Desplegar menú"
                            )
                        }
                    }
                    
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Rating (Mayor a Menor)") },
                            onClick = {
                                ordenSeleccionado = OrdenTransporte.RATING_DESC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Precio (Menor a Mayor)") },
                            onClick = {
                                ordenSeleccionado = OrdenTransporte.PRECIO_ASC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Precio (Mayor a Menor)") },
                            onClick = {
                                ordenSeleccionado = OrdenTransporte.PRECIO_DESC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Nombre (A-Z)") },
                            onClick = {
                                ordenSeleccionado = OrdenTransporte.NOMBRE_ASC
                                menuExpanded = false
                            }
                        )
                    }
                }
            }
            
            // Lista de transportes
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transportesFiltrados) { transporte ->
                ItemCard(
                    imagenRes = ImageUtils.getImageResTrans(transporte.imagen),
                    nombre = transporte.nombre,
                    descripcion = transporte.descripcion,
                    precio = transporte.precio?.toString(),
                    rating = transporte.rating,
                    ubicacion = transporte.ubicacion,
                    categoria = null,
                    tipo = transporte.tipo,
                    esFavorito = favoritosViewModel.esFavorito(transporte.id, "transporte"),
                    onFavoritoClick = { favoritosViewModel.toggleFavorito(transporte.id, "transporte") },
                    onClick = { navController.navigate("detalle/${transporte.id}/transporte") }
                )
            }
        }
    }
    }
    }
}
