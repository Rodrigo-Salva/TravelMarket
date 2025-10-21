package com.proyecto.travelmarket.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyecto.travelmarket.R
import androidx.compose.ui.graphics.Color

@Composable
fun ItemCard(
    imagenRes: Int,
    nombre: String,
    descripcion: String,
    precio: String?,
    rating: Double,
    ubicacion: String,
    categoria: String?,
    tipo: String? = null,
    esFavorito: Boolean = false,
    onFavoritoClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = imagenRes),
                    contentDescription = nombre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )
                
                // Botón de favorito (corazón)
                Surface(
                    onClick = onFavoritoClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .size(36.dp),
                    shape = RoundedCornerShape(18.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = if (esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (esFavorito) "Quitar de favoritos" else "Agregar a favoritos",
                            tint = if (esFavorito) Color(0xFFE91E63) else Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                // Rating
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "★ $rating",
                            fontSize = 14.sp,
                            color = Color(0xFFFFD700),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    if (categoria != null) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFF5F5F5),
                            shadowElevation = 0.dp
                        ) {
                            Text(
                                text = categoria,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    } else if (tipo != null) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFF5F5F5),
                            shadowElevation = 0.dp
                        ) {
                            Text(
                                text = tipo,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = descripcion,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = ubicacion,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    if (precio != null) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFF5F5F5),
                            shadowElevation = 0.dp
                        ) {
                            Text(
                                text = "S/ $precio",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
