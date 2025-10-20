package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proyecto.travelmarket.model.*
import com.proyecto.travelmarket.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val destacados by viewModel.destacados.collectAsState()
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 🔍 Barra de búsqueda
        SearchBar(
            query = searchText,
            onQueryChange = { searchText = it },
            onSearch = { /* acción al buscar */ },
            active = false,
            onActiveChange = { },
            placeholder = { Text("Busca lugares, eventos o restaurantes...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar"
                )
            },
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surface,
                dividerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {}

        // 📋 Contenido principal
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 🏙️ Banner de bienvenida
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "¡Bienvenido a Lima!",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Descubre los mejores lugares, eventos y sabores de Perú durante los Juegos Panamericanos.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // ⭐ Título de destacados
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Destacados",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Ver todos",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // 🧭 Lista de destacados
            val filtrados = destacados.filter {
                it.toString().contains(searchText, ignoreCase = true)
            }

            items(filtrados) { item ->
                ItemCard(
                    item = item,
                    onClick = {
                        val (tipo, id) = getTypeAndId(item)
                        navController.navigate("detalle/$id/$tipo")
                    }
                )
            }
        }
    }
}

@Composable
fun ItemCard(item: Any, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            when (item) {
                is Lugar -> ItemContent(
                    titulo = item.nombre,
                    descripcion = item.descripcion,
                    infoExtra = "📍 ${item.ubicacion}",
                    precio = if (item.precio > 0) "S/ ${item.precio}" else "Gratis",
                    rating = item.rating
                )

                is Evento -> ItemContent(
                    titulo = item.nombre,
                    descripcion = item.descripcion,
                    infoExtra = "🏟️ ${item.estadio}",
                    precio = "S/ ${item.precio}",
                    rating = item.rating
                )

                is Restaurante -> ItemContent(
                    titulo = item.nombre,
                    descripcion = item.descripcion,
                    infoExtra = "🍽️ ${item.tipoCocina}",
                    precio = "S/ ${item.precio}",
                    rating = item.rating
                )

                is Transporte -> ItemContent(
                    titulo = item.nombre,
                    descripcion = item.descripcion,
                    infoExtra = "🚌 ${item.tipo}",
                    precio = item.precio?.let { "S/ $it" } ?: "Consultar",
                    rating = item.rating
                )
            }
        }
    }
}

@Composable
fun ItemContent(
    titulo: String,
    descripcion: String,
    infoExtra: String,
    precio: String,
    rating: Double
) {
    Text(
        text = titulo,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = descripcion,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = infoExtra, style = MaterialTheme.typography.bodySmall)
        Text(
            text = precio,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
    Text(
        text = "⭐ $rating",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.primary
    )
}

fun getTypeAndId(item: Any): Pair<String, Int> {
    return when (item) {
        is Lugar -> "lugar" to item.id
        is Evento -> "evento" to item.id
        is Restaurante -> "restaurante" to item.id
        is Transporte -> "transporte" to item.id
        else -> "lugar" to 0
    }
}
