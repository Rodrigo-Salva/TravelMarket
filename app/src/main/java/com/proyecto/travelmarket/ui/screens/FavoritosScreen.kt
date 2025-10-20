package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proyecto.travelmarket.data.DataSource
import com.proyecto.travelmarket.model.*
import com.proyecto.travelmarket.ui.screens.components.ItemCard
import com.proyecto.travelmarket.ui.screens.components.AccountDrawerContent
import com.proyecto.travelmarket.ui.theme.Blanco
import com.proyecto.travelmarket.ui.theme.Rojo
import com.proyecto.travelmarket.ui.viewmodel.FavoritosViewModel
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModel
import com.proyecto.travelmarket.utils.ImageUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosScreen(
    navController: NavController,
    favoritosViewModel: FavoritosViewModel = viewModel(),
    authViewModel: AuthViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentUser by authViewModel.currentUser.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { 
            AccountDrawerContent(
                currentUser = currentUser,
                navController = navController,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
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
                TopAppBar(
                    title = {
                        Text(
                            text = "Mis Favoritos",
                            color = Blanco,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Volver",
                                tint = Blanco
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { 
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Menú de cuenta",
                                tint = Blanco
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Rojo
                    )
                )
            },
            bottomBar = {
                BottomNavigationBar(
                    navController = navController,
                    selectedItem = 4
                )
            }
        ) { paddingValues ->
            if (favoritosViewModel.favoritos.isEmpty()) {
                // Mostrar mensaje cuando no hay favoritos
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color(0xFFE8E8E8)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No tienes favoritos aún",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Explora lugares, eventos, restaurantes y transportes para agregarlos a tus favoritos",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                // Mostrar lista de favoritos
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color(0xFFE8E8E8)),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Contador de favoritos
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Rojo)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    tint = Blanco,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${favoritosViewModel.favoritos.size} favoritos guardados",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Blanco
                                )
                            }
                        }
                    }

                    // Lista de favoritos
                    items(favoritosViewModel.favoritos) { favItem ->
                        val item = when (favItem.tipo) {
                            "lugar" -> DataSource.lugares.find { it.id == favItem.id }
                            "evento" -> DataSource.eventos.find { it.id == favItem.id }
                            "restaurante" -> DataSource.restaurantes.find { it.id == favItem.id }
                            "transporte" -> DataSource.transportes.find { it.id == favItem.id }
                            else -> null
                        }

                        item?.let {
                            when (it) {
                                is Lugar -> ItemCard(
                                    imagenRes = ImageUtils.getImageRes(it.imagen),
                                    nombre = it.nombre,
                                    descripcion = it.descripcion,
                                    precio = if (it.precio > 0) it.precio.toString() else null,
                                    rating = it.rating,
                                    ubicacion = it.ubicacion,
                                    categoria = it.categoria,
                                    esFavorito = true,
                                    onFavoritoClick = { favoritosViewModel.toggleFavorito(it.id, "lugar") },
                                    onClick = { navController.navigate("detalle/${it.id}/lugar") }
                                )
                                is Evento -> ItemCard(
                                    imagenRes = ImageUtils.getImageResEvent(it.imagen),
                                    nombre = it.nombre,
                                    descripcion = it.descripcion,
                                    precio = it.precio.toString(),
                                    rating = it.rating,
                                    ubicacion = it.estadio,
                                    categoria = it.deporte,
                                    esFavorito = true,
                                    onFavoritoClick = { favoritosViewModel.toggleFavorito(it.id, "evento") },
                                    onClick = { navController.navigate("detalle/${it.id}/evento") }
                                )
                                is Restaurante -> ItemCard(
                                    imagenRes = ImageUtils.getImageResRest(it.imagen),
                                    nombre = it.nombre,
                                    descripcion = it.descripcion,
                                    precio = it.precio.toString(),
                                    rating = it.rating,
                                    ubicacion = it.ubicacion,
                                    categoria = it.tipoCocina,
                                    esFavorito = true,
                                    onFavoritoClick = { favoritosViewModel.toggleFavorito(it.id, "restaurante") },
                                    onClick = { navController.navigate("detalle/${it.id}/restaurante") }
                                )
                                is Transporte -> ItemCard(
                                    imagenRes = ImageUtils.getImageResTrans(it.imagen),
                                    nombre = it.nombre,
                                    descripcion = it.descripcion,
                                    precio = it.precio?.toString(),
                                    rating = it.rating,
                                    ubicacion = it.ubicacion,
                                    categoria = null,
                                    tipo = it.tipo,
                                    esFavorito = true,
                                    onFavoritoClick = { favoritosViewModel.toggleFavorito(it.id, "transporte") },
                                    onClick = { navController.navigate("detalle/${it.id}/transporte") }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
