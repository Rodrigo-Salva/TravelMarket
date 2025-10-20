package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proyecto.travelmarket.R
import com.proyecto.travelmarket.data.DataSource
import com.proyecto.travelmarket.model.*
import com.proyecto.travelmarket.ui.screens.components.ItemCard
import com.proyecto.travelmarket.ui.screens.components.AccountDrawerContent
import com.proyecto.travelmarket.ui.theme.Blanco
import com.proyecto.travelmarket.ui.theme.Rojo
import com.proyecto.travelmarket.ui.viewmodel.FavoritosViewModel
import com.proyecto.travelmarket.ui.viewmodel.AuthViewModel
import com.proyecto.travelmarket.utils.ImageUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    favoritosViewModel: FavoritosViewModel = viewModel(),
    authViewModel: AuthViewModel
) {
    var searchText by remember { mutableStateOf("") }
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
                        text = "TravelMarket",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Blanco,
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
                            tint = Blanco
                        )
                    }
                }

                Text(
                    text = "Lima - Juegos Panamericanos",
                    fontSize = 16.sp,
                    color = Blanco,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Barra de búsqueda
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    placeholder = { Text("Busca a Nivel Nacional") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Blanco,
                        unfocusedContainerColor = Blanco,
                        focusedBorderColor = Blanco,
                        unfocusedBorderColor = Blanco
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectedItem = 2
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE8E8E8)),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Banner de bienvenida
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD9D9D9)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "¡Bienvenido a Lima!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Descubre los mejores lugares, eventos y sabores de Perú durante los Juegos Panamericanos",
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Elementos destacados ordenados
            val destacadosOrdenados = listOf(
                DataSource.lugares.filter { it.destacado },
                DataSource.eventos.filter { it.destacado },
                DataSource.restaurantes.filter { it.destacado },
                DataSource.transportes.filter { it.destacado }
            ).flatten()

            // Sección de Favoritos
            if (favoritosViewModel.favoritos.isNotEmpty()) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color(0xFFE91E63),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mis Favoritos",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }

                // Mostrar solo los primeros 2 favoritos
                items(favoritosViewModel.favoritos.take(2)) { favItem ->
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
                
                // Botón "Ver más" si hay más de 2 favoritos
                if (favoritosViewModel.favoritos.size > 2) {
                    item {
                        Button(
                            onClick = { navController.navigate("favoritos") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Rojo
                            ),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(2.dp, Rojo)
                        ) {
                            Text(
                                text = "Ver más (${favoritosViewModel.favoritos.size - 2} más)",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            // Sección de Destacados
            item {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
            
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Destacados",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

            items(destacadosOrdenados) { item ->
                when (item) {
                    is Lugar -> ItemCard(
                        imagenRes = ImageUtils.getImageRes(item.imagen),
                        nombre = item.nombre,
                        descripcion = item.descripcion,
                        precio = if (item.precio > 0) item.precio.toString() else null,
                        rating = item.rating,
                        ubicacion = item.ubicacion,
                        categoria = item.categoria,
                        esFavorito = favoritosViewModel.esFavorito(item.id, "lugar"),
                        onFavoritoClick = { favoritosViewModel.toggleFavorito(item.id, "lugar") },
                        onClick = { navController.navigate("detalle/${item.id}/lugar") }
                    )
                    is Evento -> ItemCard(
                        imagenRes = ImageUtils.getImageResEvent(item.imagen),
                        nombre = item.nombre,
                        descripcion = item.descripcion,
                        precio = item.precio.toString(),
                        rating = item.rating,
                        ubicacion = item.estadio,
                        categoria = item.deporte,
                        esFavorito = favoritosViewModel.esFavorito(item.id, "evento"),
                        onFavoritoClick = { favoritosViewModel.toggleFavorito(item.id, "evento") },
                        onClick = { navController.navigate("detalle/${item.id}/evento") }
                    )
                    is Restaurante -> ItemCard(
                        imagenRes = ImageUtils.getImageResRest(item.imagen),
                        nombre = item.nombre,
                        descripcion = item.descripcion,
                        precio = item.precio.toString(),
                        rating = item.rating,
                        ubicacion = item.ubicacion,
                        categoria = item.tipoCocina,
                        esFavorito = favoritosViewModel.esFavorito(item.id, "restaurante"),
                        onFavoritoClick = { favoritosViewModel.toggleFavorito(item.id, "restaurante") },
                        onClick = { navController.navigate("detalle/${item.id}/restaurante") }
                    )
                    is Transporte -> ItemCard(
                        imagenRes = ImageUtils.getImageResTrans(item.imagen),
                        nombre = item.nombre,
                        descripcion = item.descripcion,
                        precio = item.precio?.toString(),
                        rating = item.rating,
                        ubicacion = item.ubicacion,
                        categoria = null,
                        tipo = item.tipo,
                        esFavorito = favoritosViewModel.esFavorito(item.id, "transporte"),
                        onFavoritoClick = { favoritosViewModel.toggleFavorito(item.id, "transporte") },
                        onClick = { navController.navigate("detalle/${item.id}/transporte") }
                    )
                }
            }
        }
    }
    }
}
