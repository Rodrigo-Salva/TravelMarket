package com.proyecto.travelmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.outlined.Star
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.proyecto.travelmarket.model.*
import com.proyecto.travelmarket.ui.viewmodel.DetalleViewModel
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.FlowRow

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

    val titleText = when (val currentItem = item) {
        is Lugar -> currentItem.nombre
        is Evento -> currentItem.nombre
        is Restaurante -> currentItem.nombre
        is Transporte -> currentItem.nombre
        else -> "Detalle"
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text(titleText, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
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

        DetalleHeader(
            nombre = lugar.nombre,
            rating = lugar.rating,
            precio = if (lugar.precio > 0) "S/ ${lugar.precio}" else "Gratis"
        )
        QuickInfoChipsRow(
            chips = listOf(
                "Ubicación" to lugar.ubicacion,
                "Categoría" to lugar.categoria,
                "Horario" to lugar.horario
            )
        )
        DetalleInfo(
            infoList = listOf(
                Triple("📍", "Ubicación", lugar.ubicacion),
                Triple("🏛️", "Categoría", lugar.categoria),
                Triple("🕐", "Horario", lugar.horario)
            )
        )
        DetalleDescripcion(lugar.descripcionDetallada)
        ActionButtons(mapQuery = lugar.ubicacion, shareText = "${lugar.nombre} — ${lugar.categoria}\n${lugar.ubicacion}")
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
        QuickInfoChipsRow(
            chips = listOf(
                "Estadio" to evento.estadio,
                "Deporte" to evento.deporte,
                "Fecha" to evento.fecha
            )
        )
        DetalleInfo(
            infoList = listOf(
                Triple("🏟️", "Estadio", evento.estadio),
                Triple("⚽", "Deporte", evento.deporte),
                Triple("📅", "Fecha", evento.fecha),
                Triple("🕐", "Horario", evento.hora)
            )
        )
        DetalleDescripcion(evento.descripcionDetallada)
        ActionButtons(mapQuery = evento.estadio, shareText = "${evento.nombre} — ${evento.deporte}\n${evento.estadio} • ${evento.fecha} ${evento.hora}")
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
        QuickInfoChipsRow(
            chips = listOf(
                "Tipo" to restaurante.tipoCocina,
                "Especialidad" to restaurante.especialidad,
                "Horario" to restaurante.horario
            )
        )
        DetalleInfo(
            infoList = listOf(
                Triple("🍽️", "Tipo de Cocina", restaurante.tipoCocina),
                Triple("⭐", "Especialidad", restaurante.especialidad),
                Triple("📍", "Ubicación", restaurante.ubicacion),
                Triple("🕐", "Horario", restaurante.horario)
            )
        )
        DetalleDescripcion(restaurante.descripcionDetallada)
        ActionButtons(mapQuery = restaurante.ubicacion, shareText = "${restaurante.nombre} — ${restaurante.tipoCocina}\n${restaurante.ubicacion}")
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
        QuickInfoChipsRow(
            chips = listOf(
                "Tipo" to transporte.tipo,
                "Ruta" to transporte.ruta,
                "Horario" to transporte.horario
            )
        )
        DetalleInfo(
            infoList = listOf(
                Triple("🚌", "Tipo", transporte.tipo),
                Triple("🗺️", "Ruta", transporte.ruta),
                Triple("📍", "Ubicación", transporte.ubicacion),
                Triple("🕐", "Horario", transporte.horario)
            )
        )
        DetalleDescripcion(transporte.descripcionDetallada)
        ActionButtons(mapQuery = transporte.ubicacion, shareText = "${transporte.nombre} — ${transporte.tipo}\n${transporte.ubicacion}")
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
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    RatingStars(rating = rating)
                    Text(
                        text = String.format("%.1f", rating),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                PriceChip(text = precio)
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

// ================= EXTRAS ESTÉTICOS =================
@Composable
fun RatingStars(rating: Double, maxStars: Int = 5) {
    val fullStars = rating.toInt().coerceIn(0, maxStars)
    val hasHalf = (rating - fullStars) >= 0.5 && fullStars < maxStars
    val emptyStars = maxStars - fullStars - if (hasHalf) 1 else 0
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        repeat(fullStars) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }
        if (hasHalf) {
            Icon(imageVector = Icons.Filled.StarHalf, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }
        repeat(emptyStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun PriceChip(text: String) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun QuickInfoChipsRow(chips: List<Pair<String, String>>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        chips.forEach { (label, value) ->
            AssistChip(
                onClick = {},
                label = { Text("$label: $value", maxLines = 1) }
            )
        }
    }
}

@Composable
fun ActionButtons(mapQuery: String?, shareText: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = {
                mapQuery?.takeIf { it.isNotBlank() }?.let {
                    val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(it))
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                        setPackage("com.google.android.apps.maps")
                    }
                    context.startActivity(mapIntent)
                }
            },
            enabled = !mapQuery.isNullOrBlank()
        ) {
            Text("Ver en mapa")
        }
        OutlinedButton(onClick = {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }) {
            Text("Compartir")
        }
    }
}
