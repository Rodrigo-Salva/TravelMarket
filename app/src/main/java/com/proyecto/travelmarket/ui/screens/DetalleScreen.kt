package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proyecto.travelmarket.data.DataSource
import com.proyecto.travelmarket.ui.theme.Blanco
import com.proyecto.travelmarket.ui.theme.Rojo
import com.proyecto.travelmarket.ui.viewmodel.FavoritosViewModel
import com.proyecto.travelmarket.utils.ImageUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    navController: NavController,
    itemId: Int,
    tipo: String,
    favoritosViewModel: FavoritosViewModel = viewModel()
) {
    // Obtener el item según el tipo y ID
    val item = when (tipo) {
        "lugar" -> DataSource.lugares.find { it.id == itemId }
        "evento" -> DataSource.eventos.find { it.id == itemId }
        "restaurante" -> DataSource.restaurantes.find { it.id == itemId }
        "transporte" -> DataSource.transportes.find { it.id == itemId }
        else -> null
    }

    val esFavorito = favoritosViewModel.esFavorito(itemId, tipo)

    if (item == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Item no encontrado")
        }
        return
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen principal
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                val imagenRes = when (tipo) {
                    "lugar" -> ImageUtils.getImageRes((item as com.proyecto.travelmarket.model.Lugar).imagen)
                    "evento" -> ImageUtils.getImageResEvent((item as com.proyecto.travelmarket.model.Evento).imagen)
                    "restaurante" -> ImageUtils.getImageResRest((item as com.proyecto.travelmarket.model.Restaurante).imagen)
                    "transporte" -> ImageUtils.getImageResTrans((item as com.proyecto.travelmarket.model.Transporte).imagen)
                    else -> com.proyecto.travelmarket.R.drawable.ic_launcher_background
                }
                
                Image(
                    painter = painterResource(id = imagenRes),
                    contentDescription = when (item) {
                        is com.proyecto.travelmarket.model.Lugar -> item.nombre
                        is com.proyecto.travelmarket.model.Evento -> item.nombre
                        is com.proyecto.travelmarket.model.Restaurante -> item.nombre
                        is com.proyecto.travelmarket.model.Transporte -> item.nombre
                        else -> ""
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                
                // Botón de regreso
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                        .background(Blanco, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.Black
                    )
                }
                
                // Botón de favorito (corazón circular)
                IconButton(
                    onClick = { favoritosViewModel.toggleFavorito(itemId, tipo) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .size(48.dp)
                        .background(Blanco, CircleShape)
                ) {
                    Icon(
                        imageVector = if (esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (esFavorito) "Quitar de favoritos" else "Agregar a favoritos",
                        tint = if (esFavorito) Color(0xFFE91E63) else Color.Gray
                    )
                }
            }
            
            // Contenido con información
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // Título
                    Text(
                        text = when (item) {
                            is com.proyecto.travelmarket.model.Lugar -> item.nombre
                            is com.proyecto.travelmarket.model.Evento -> item.nombre
                            is com.proyecto.travelmarket.model.Restaurante -> item.nombre
                            is com.proyecto.travelmarket.model.Transporte -> item.nombre
                            else -> ""
                        },
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Descripción detallada
                    Text(
                        text = when (item) {
                            is com.proyecto.travelmarket.model.Lugar -> item.descripcionDetallada
                            is com.proyecto.travelmarket.model.Evento -> item.descripcionDetallada
                            is com.proyecto.travelmarket.model.Restaurante -> item.descripcionDetallada
                            is com.proyecto.travelmarket.model.Transporte -> item.descripcionDetallada
                            else -> ""
                        },
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        lineHeight = 24.sp
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Información adicional
                    when (item) {
                        is com.proyecto.travelmarket.model.Lugar -> {
                            InfoRowDetalle(Icons.Default.LocationOn, "Ubicación", item.ubicacion)
                            InfoRowDetalle(Icons.Default.Place, "Categoría", item.categoria)
                            InfoRowDetalle(Icons.Default.Schedule, "Horario", item.horario)
                            InfoRowDetalle(Icons.Default.Star, "Rating", item.rating.toString())
                            InfoRowDetalle(Icons.Default.AttachMoney, "Precio", if (item.precio > 0) "S/ ${item.precio}" else "Gratis")
                        }
                        is com.proyecto.travelmarket.model.Evento -> {
                            InfoRowDetalle(Icons.Default.Stadium, "Estadio", item.estadio)
                            InfoRowDetalle(Icons.Default.SportsScore, "Deporte", item.deporte)
                            InfoRowDetalle(Icons.Default.CalendarToday, "Fecha", item.fecha)
                            InfoRowDetalle(Icons.Default.AccessTime, "Hora", item.hora)
                            InfoRowDetalle(Icons.Default.Star, "Rating", item.rating.toString())
                            InfoRowDetalle(Icons.Default.AttachMoney, "Precio", "S/ ${item.precio}")
                        }
                        is com.proyecto.travelmarket.model.Restaurante -> {
                            InfoRowDetalle(Icons.Default.LocationOn, "Ubicación", item.ubicacion)
                            InfoRowDetalle(Icons.Default.Restaurant, "Tipo de Cocina", item.tipoCocina)
                            InfoRowDetalle(Icons.Default.Star, "Especialidad", item.especialidad)
                            InfoRowDetalle(Icons.Default.Schedule, "Horario", item.horario)
                            InfoRowDetalle(Icons.Default.Star, "Rating", item.rating.toString())
                            InfoRowDetalle(Icons.Default.AttachMoney, "Precio Promedio", "S/ ${item.precio}")
                        }
                        is com.proyecto.travelmarket.model.Transporte -> {
                            InfoRowDetalle(Icons.Default.LocationOn, "Ubicación", item.ubicacion)
                            InfoRowDetalle(Icons.Default.DirectionsBus, "Tipo", item.tipo)
                            InfoRowDetalle(Icons.Default.Map, "Ruta", item.ruta)
                            InfoRowDetalle(Icons.Default.Schedule, "Horario", item.horario)
                            InfoRowDetalle(Icons.Default.Star, "Rating", item.rating.toString())
                            if (item.precio != null) {
                                InfoRowDetalle(Icons.Default.AttachMoney, "Precio", "S/ ${item.precio}")
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Botón "Agregar a Favoritos"
                    if (esFavorito) {
                        OutlinedButton(
                            onClick = { favoritosViewModel.toggleFavorito(itemId, tipo) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Rojo
                            ),
                            border = BorderStroke(2.dp, Rojo),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Quitar de Favoritos",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Rojo
                            )
                        }
                    } else {
                        Button(
                            onClick = { favoritosViewModel.toggleFavorito(itemId, tipo) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Rojo
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Agregar a Favoritos",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Botón "Salir"
                    OutlinedButton(
                        onClick = { 
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = false }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Gray
                        ),
                        border = BorderStroke(2.dp, Color.Gray),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Salir",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

@Composable
fun InfoRowDetalle(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
        }
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
