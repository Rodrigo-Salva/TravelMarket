package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.travelmarket.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("Destacados") }

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
                    Column {
                        Text(
                            text = "TravelMarket",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Blanco,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menú",
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
     //               colors = TextFieldDefaults.outlinedTextFieldColors(
     //                   containerColor = Blanco,
     //                   focusedBorderColor = Blanco,
     //                   unfocusedBorderColor = Blanco
    //                ),
                    shape = RoundedCornerShape(24.dp)
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE8E8E8))
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
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }

            // Tabs: Destacados / Mejor valorados
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Destacados",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedTab == "Destacados") Color.Black else Color.Gray,
                        modifier = Modifier.clickable { selectedTab = "Destacados" }
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Mejor valorados",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = if (selectedTab == "Mejor valorados") Color.Black else Color.Gray,
                            modifier = Modifier.clickable { selectedTab = "Mejor valorados" }
                        )
                    }
                }
            }

            // Tarjetas de destinos
            items(3) { index ->
                DestinoCard(
                    nombre = when(index) {
                        0 -> "Machu Picchu"
                        1 -> "Plaza Mayor de Lima"
                        else -> "Circuito Mágico del Agua"
                    },
                    descripcion = when(index) {
                        0 -> "La ciudadela inca más famosa del mundo, Patrimonio de la Humanidad y una de las Siete Maravillas del Mundo"
                        1 -> "Corazón del Centro Histórico de Lima, Patrimonio de la Humanidad. Rodeada del Palacio de Gobierno"
                        else -> "Complejo de fuentes ornamentales"
                    },
                    ubicacion = when(index) {
                        0 -> "Aguas Calientes, Cusco"
                        1 -> "Jr. de la Unión, Cercado de Lima"
                        else -> "Parque de la Reserva, Lima"
                    },
                    precio = when(index) {
                        0 -> "S/ 152"
                        1 -> "S/ 0"
                        else -> "S/ 4"
                    },
                    valoracion = when(index) {
                        0 -> "5"
                        else -> "4.8"
                    }
                )
            }
        }
    }
}

@Composable
fun DestinoCard(
    nombre: String,
    descripcion: String,
    ubicacion: String,
    precio: String,
    valoracion: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Blanco)
    ) {
        Column {
            // Imagen del destino
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFF4CAF50)) // Color placeholder
            ) {
                // Badge de valoración
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = Blanco
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = valoracion,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Información del destino
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = precio,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = descripcion,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = ubicacion,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}