package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.res.painterResource
import com.proyecto.travelmarket.model.*
import com.proyecto.travelmarket.ui.viewmodel.DetalleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    navController: NavController,
    id: String?,
    tipo: String?,
    viewModel: DetalleViewModel = viewModel()
) {
    val item by viewModel.detalleItem.collectAsState()

    // Cargar datos cuando cambian los parámetros
    LaunchedEffect(id, tipo) {
        id?.toIntOrNull()?.let { itemId ->
            tipo?.let { itemType ->
                viewModel.loadDetalle(itemType, itemId)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val currentItem = item) {
                is Lugar -> DetalleLugar(currentItem)
                is Evento -> DetalleEvento(currentItem)
                is Restaurante -> DetalleRestaurante(currentItem)
                is Transporte -> DetalleTransporte(currentItem)
                null -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

// Función auxiliar para obtener drawable por nombre
@Composable
fun getImageIdByName(name: String): Int {
    return LocalContext.current.resources.getIdentifier(name, "drawable", LocalContext.current.packageName)
}

// ================= DETALLE LUGAR =================
@Composable
fun DetalleLugar(lugar: Lugar) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = getImageIdByName(lugar.imagen)),
            contentDescription = lugar.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        DetalleHeader(nombre = lugar.nombre, rating = lugar.rating, precio = if (lugar.precio > 0) "S/ ${lugar.precio}" else "Gratis")
        DetalleInfo(
            infoList = listOf(
                Triple("📍", "Ubicación", lugar.ubicacion),
                Triple("🏛️", "Categoría", lugar.categoria),
                Triple("🕐", "Horario", lugar.horario)
            )
        )
        DetalleDescripcion(lugar.descripcionDetallada)
    }
}

// ================= DETALLE EVENTO =================
@Composable
fun DetalleEvento(evento: Evento) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = getImageIdByName(evento.imagen)),
            contentDescription = evento.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        DetalleHeader(nombre = evento.nombre, rating = evento.rating, precio = "S/ ${evento.precio}")
        DetalleInfo(
            infoList = listOf(
                Triple("🏟️", "Estadio", evento.estadio),
                Triple("⚽", "Deporte", evento.deporte),
                Triple("📅", "Fecha", evento.fecha),
                Triple("🕐", "Horario", evento.hora)
            )
        )
        DetalleDescripcion(evento.descripcionDetallada)
    }
}

// ================= DETALLE RESTAURANTE =================
@Composable
fun DetalleRestaurante(restaurante: Restaurante) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = getImageIdByName(restaurante.imagen)),
            contentDescription = restaurante.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        DetalleHeader(nombre = restaurante.nombre, rating = restaurante.rating, precio = "S/ ${restaurante.precio}")
        DetalleInfo(
            infoList = listOf(
                Triple("🍽️", "Tipo de Cocina", restaurante.tipoCocina),
                Triple("⭐", "Especialidad", restaurante.especialidad),
                Triple("📍", "Ubicación", restaurante.ubicacion),
                Triple("🕐", "Horario", restaurante.horario)
            )
        )
        DetalleDescripcion(restaurante.descripcionDetallada)
    }
}

// ================= DETALLE TRANSPORTE =================
@Composable
fun DetalleTransporte(transporte: Transporte) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = getImageIdByName(transporte.imagen)),
            contentDescription = transporte.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        DetalleHeader(nombre = transporte.nombre, rating = transporte.rating, precio = transporte.precio?.let { "S/ $it" } ?: "Consultar")
        DetalleInfo(
            infoList = listOf(
                Triple("🚌", "Tipo", transporte.tipo),
                Triple("🗺️", "Ruta", transporte.ruta),
                Triple("📍", "Ubicación", transporte.ubicacion),
                Triple("🕐", "Horario", transporte.horario)
            )
        )
        DetalleDescripcion(transporte.descripcionDetallada)
    }
}

// ================= COMPONENTES REUTILIZABLES =================
@Composable
fun DetalleHeader(nombre: String, rating: Double, precio: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "⭐ $rating",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = precio,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun DetalleInfo(infoList: List<Triple<String, String, String>>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            infoList.forEachIndexed { index, item ->
                InfoRow(icon = item.first, label = item.second, value = item.third)
                if (index < infoList.size - 1) Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
fun DetalleDescripcion(texto: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Descripción",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = texto,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )
        }
    }
}

@Composable
fun InfoRow(icon: String, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$icon $label",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}
