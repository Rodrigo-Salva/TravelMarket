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
import com.proyecto.travelmarket.ui.theme.Rojo
import com.proyecto.travelmarket.ui.viewmodel.FavoritosViewModel
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModel
import com.proyecto.travelmarket.utils.ImageUtils

enum class OrdenEventos {
    RATING_DESC,
    PRECIO_ASC,
    PRECIO_DESC,
    NOMBRE_ASC
}

@Composable
fun EventosScreen(
    navController: NavController,
    favoritosViewModel: FavoritosViewModel = viewModel(),
    authViewModel: AuthViewModel
) {
    var ordenSeleccionado by remember { mutableStateOf(OrdenEventos.RATING_DESC) }
    var menuExpanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentUser by authViewModel.currentUser.collectAsState()
    
    val eventosOrdenados = remember(ordenSeleccionado) {
        when (ordenSeleccionado) {
            OrdenEventos.RATING_DESC -> DataSource.eventos.sortedByDescending { it.rating }
            OrdenEventos.PRECIO_ASC -> DataSource.eventos.sortedBy { it.precio }
            OrdenEventos.PRECIO_DESC -> DataSource.eventos.sortedByDescending { it.precio }
            OrdenEventos.NOMBRE_ASC -> DataSource.eventos.sortedBy { it.nombre }
        }
    }
    
    val eventosFiltrados = remember(eventosOrdenados, searchText) {
        if (searchText.isNotEmpty()) {
            eventosOrdenados.filter { evento ->
                evento.nombre.contains(searchText, ignoreCase = true) ||
                evento.descripcion.contains(searchText, ignoreCase = true) ||
                evento.deporte.contains(searchText, ignoreCase = true)
            }
        } else {
            eventosOrdenados
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
                        popUpTo("eventos") { inclusive = true }
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
                        text = "Eventos Deportivos",
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
                    text = "Juegos Panamericanos - Lima",
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
                    placeholder = { Text("Buscar eventos deportivos") },
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
                selectedItem = 1
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
                                    OrdenEventos.RATING_DESC -> "Ordenar por: Rating"
                                    OrdenEventos.PRECIO_ASC -> "Ordenar por: Precio (menor)"
                                    OrdenEventos.PRECIO_DESC -> "Ordenar por: Precio (mayor)"
                                    OrdenEventos.NOMBRE_ASC -> "Ordenar por: Nombre (A-Z)"
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
                                ordenSeleccionado = OrdenEventos.RATING_DESC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Precio (Menor a Mayor)") },
                            onClick = {
                                ordenSeleccionado = OrdenEventos.PRECIO_ASC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Precio (Mayor a Menor)") },
                            onClick = {
                                ordenSeleccionado = OrdenEventos.PRECIO_DESC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Nombre (A-Z)") },
                            onClick = {
                                ordenSeleccionado = OrdenEventos.NOMBRE_ASC
                                menuExpanded = false
                            }
                        )
                    }
                }
            }
            
            // Lista de eventos
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(eventosFiltrados) { evento ->
                ItemCard(
                    imagenRes = ImageUtils.getImageResEvent(evento.imagen),
                    nombre = evento.nombre,
                    descripcion = evento.descripcion,
                    precio = evento.precio.toString(),
                    rating = evento.rating,
                    ubicacion = evento.estadio,
                    categoria = evento.deporte,
                    esFavorito = favoritosViewModel.esFavorito(evento.id, "evento"),
                    onFavoritoClick = { favoritosViewModel.toggleFavorito(evento.id, "evento") },
                    onClick = { navController.navigate("detalle/${evento.id}/evento") }
                )
            }
        }
    }
    }
    }
}
