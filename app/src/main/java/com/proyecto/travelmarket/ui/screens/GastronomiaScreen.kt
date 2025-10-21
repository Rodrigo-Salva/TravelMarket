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

enum class OrdenRestaurantes {
    RATING_DESC,
    PRECIO_ASC,
    PRECIO_DESC,
    NOMBRE_ASC
}

@Composable
fun GastronomiaScreen(
    navController: NavController,
    favoritosViewModel: FavoritosViewModel = viewModel(),
    authViewModel: AuthViewModel
) {
    var ordenSeleccionado by remember { mutableStateOf(OrdenRestaurantes.RATING_DESC) }
    var menuExpanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentUser by authViewModel.currentUser.collectAsState()
    
    val restaurantesOrdenados = remember(ordenSeleccionado) {
        when (ordenSeleccionado) {
            OrdenRestaurantes.RATING_DESC -> DataSource.restaurantes.sortedByDescending { it.rating }
            OrdenRestaurantes.PRECIO_ASC -> DataSource.restaurantes.sortedBy { it.precio }
            OrdenRestaurantes.PRECIO_DESC -> DataSource.restaurantes.sortedByDescending { it.precio }
            OrdenRestaurantes.NOMBRE_ASC -> DataSource.restaurantes.sortedBy { it.nombre }
        }
    }
    
    val restaurantesFiltrados = remember(restaurantesOrdenados, searchText) {
        if (searchText.isNotEmpty()) {
            restaurantesOrdenados.filter { restaurante ->
                restaurante.nombre.contains(searchText, ignoreCase = true) ||
                restaurante.descripcion.contains(searchText, ignoreCase = true) ||
                restaurante.tipoCocina.contains(searchText, ignoreCase = true)
            }
        } else {
            restaurantesOrdenados
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
                        popUpTo("gastronomia") { inclusive = true }
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
                        text = "Gastronomía",
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
                    text = "Restaurantes y Sabores del Perú",
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
                    placeholder = { Text("Buscar restaurantes") },
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
                selectedItem = 3
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
                                    OrdenRestaurantes.RATING_DESC -> "Ordenar por: Rating"
                                    OrdenRestaurantes.PRECIO_ASC -> "Ordenar por: Precio (menor)"
                                    OrdenRestaurantes.PRECIO_DESC -> "Ordenar por: Precio (mayor)"
                                    OrdenRestaurantes.NOMBRE_ASC -> "Ordenar por: Nombre (A-Z)"
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
                                ordenSeleccionado = OrdenRestaurantes.RATING_DESC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Precio (Menor a Mayor)") },
                            onClick = {
                                ordenSeleccionado = OrdenRestaurantes.PRECIO_ASC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Precio (Mayor a Menor)") },
                            onClick = {
                                ordenSeleccionado = OrdenRestaurantes.PRECIO_DESC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Nombre (A-Z)") },
                            onClick = {
                                ordenSeleccionado = OrdenRestaurantes.NOMBRE_ASC
                                menuExpanded = false
                            }
                        )
                    }
                }
            }
            
            // Lista de restaurantes
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(restaurantesFiltrados) { restaurante ->
                ItemCard(
                    imagenRes = ImageUtils.getImageResRest(restaurante.imagen),
                    nombre = restaurante.nombre,
                    descripcion = restaurante.descripcion,
                    precio = restaurante.precio.toString(),
                    rating = restaurante.rating,
                    ubicacion = restaurante.ubicacion,
                    categoria = restaurante.tipoCocina,
                    esFavorito = favoritosViewModel.esFavorito(restaurante.id, "restaurante"),
                    onFavoritoClick = { favoritosViewModel.toggleFavorito(restaurante.id, "restaurante") },
                    onClick = { navController.navigate("detalle/${restaurante.id}/restaurante") }
                )
            }
        }
    }
    }
    }
}
