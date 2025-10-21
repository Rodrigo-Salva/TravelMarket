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

enum class OrdenLugares {
    RATING_DESC,
    PRECIO_ASC,
    PRECIO_DESC,
    NOMBRE_ASC
}

@Composable
fun LugaresScreen(
    navController: NavController,
    favoritosViewModel: FavoritosViewModel = viewModel(),
    authViewModel: AuthViewModel
) {
    var ordenSeleccionado by remember { mutableStateOf(OrdenLugares.RATING_DESC) }
    var menuExpanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentUser by authViewModel.currentUser.collectAsState()
    
    val lugaresOrdenados = remember(ordenSeleccionado) {
        when (ordenSeleccionado) {
            OrdenLugares.RATING_DESC -> DataSource.lugares.sortedByDescending { it.rating }
            OrdenLugares.PRECIO_ASC -> DataSource.lugares.sortedBy { it.precio }
            OrdenLugares.PRECIO_DESC -> DataSource.lugares.sortedByDescending { it.precio }
            OrdenLugares.NOMBRE_ASC -> DataSource.lugares.sortedBy { it.nombre }
        }
    }
    
    val lugaresFiltrados = remember(lugaresOrdenados, searchText) {
        if (searchText.isNotEmpty()) {
            lugaresOrdenados.filter { lugar ->
                lugar.nombre.contains(searchText, ignoreCase = true) ||
                lugar.descripcion.contains(searchText, ignoreCase = true) ||
                lugar.categoria.contains(searchText, ignoreCase = true)
            }
        } else {
            lugaresOrdenados
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
                        popUpTo("lugares") { inclusive = true }
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
                        text = "Lugares Turísticos",
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
                    text = "Lima - Perú",
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
                    placeholder = { Text("Buscar sitios turísticos") },
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
                selectedItem = 0
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
                                    OrdenLugares.RATING_DESC -> "Ordenar por: Rating"
                                    OrdenLugares.PRECIO_ASC -> "Ordenar por: Precio (menor)"
                                    OrdenLugares.PRECIO_DESC -> "Ordenar por: Precio (mayor)"
                                    OrdenLugares.NOMBRE_ASC -> "Ordenar por: Nombre (A-Z)"
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
                                ordenSeleccionado = OrdenLugares.RATING_DESC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Precio (Menor a Mayor)") },
                            onClick = {
                                ordenSeleccionado = OrdenLugares.PRECIO_ASC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Precio (Mayor a Menor)") },
                            onClick = {
                                ordenSeleccionado = OrdenLugares.PRECIO_DESC
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Nombre (A-Z)") },
                            onClick = {
                                ordenSeleccionado = OrdenLugares.NOMBRE_ASC
                                menuExpanded = false
                            }
                        )
                    }
                }
            }
            
            // Lista de lugares
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(lugaresFiltrados) { lugar ->
                    ItemCard(
                        imagenRes = ImageUtils.getImageRes(lugar.imagen),
                        nombre = lugar.nombre,
                        descripcion = lugar.descripcion,
                        precio = if (lugar.precio > 0) lugar.precio.toString() else null,
                        rating = lugar.rating,
                        ubicacion = lugar.ubicacion,
                        categoria = lugar.categoria,
                        esFavorito = favoritosViewModel.esFavorito(lugar.id, "lugar"),
                        onFavoritoClick = { favoritosViewModel.toggleFavorito(lugar.id, "lugar") },
                        onClick = { navController.navigate("detalle/${lugar.id}/lugar") }
                    )
                }
            }
        }
    }
    }
}
