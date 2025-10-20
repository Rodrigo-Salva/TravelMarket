package com.proyecto.travelmarket.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.travelmarket.model.User
import com.proyecto.travelmarket.ui.theme.Rojo

@Composable
fun AccountDrawerContent(
    currentUser: User?,
    navController: NavController,
    onLogout: () -> Unit,
    onCloseDrawer: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = Color.White
    ) {
        // Header del drawer con fondo rojo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Rojo)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Perfil",
                modifier = Modifier.size(80.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = currentUser?.nombre ?: "Usuario",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "@${currentUser?.usuario ?: "usuario"}",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Divider()
        
        // Sección de Navegación
        Text(
            text = "EXPLORAR",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        NavigationDrawerItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
            label = { Text("Inicio") },
            selected = false,
            onClick = {
                navController.navigate("home")
                onCloseDrawer()
            },
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        
        NavigationDrawerItem(
            icon = { Icon(Icons.Outlined.LocationOn, contentDescription = null) },
            label = { Text("Lugares Turísticos") },
            selected = false,
            onClick = {
                navController.navigate("lugares")
                onCloseDrawer()
            },
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        
        NavigationDrawerItem(
            icon = { Icon(Icons.Outlined.EmojiEvents, contentDescription = null) },
            label = { Text("Eventos Deportivos") },
            selected = false,
            onClick = {
                navController.navigate("eventos")
                onCloseDrawer()
            },
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        
        NavigationDrawerItem(
            icon = { Icon(Icons.Outlined.Restaurant, contentDescription = null) },
            label = { Text("Gastronomía") },
            selected = false,
            onClick = {
                navController.navigate("gastronomia")
                onCloseDrawer()
            },
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        
        NavigationDrawerItem(
            icon = { Icon(Icons.Outlined.DirectionsBus, contentDescription = null) },
            label = { Text("Transporte") },
            selected = false,
            onClick = {
                navController.navigate("transporte")
                onCloseDrawer()
            },
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = null, tint = Rojo) },
            label = { Text("Favoritos", color = Rojo) },
            selected = false,
            onClick = {
                navController.navigate("favoritos")
                onCloseDrawer()
            },
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        
        // Sobre Nosotros
        Text(
            text = "INFORMACIÓN",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            label = { 
                Column {
                    Text(
                        text = "Sobre Nosotros",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Diseñado por:",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "• Miguel Carasas",
                        fontSize = 11.sp
                    )
                    Text(
                        text = "• Rafael Chuco",
                        fontSize = 11.sp
                    )
                    Text(
                        text = "• Rodrigo Salva",
                        fontSize = 11.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Primera Versión - 2025",
                        fontSize = 10.sp,
                        color = Color.Gray,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }
            },
            selected = false,
            onClick = { },
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        Divider()
        
        // Cerrar Sesión al final
        NavigationDrawerItem(
            icon = { 
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    tint = Rojo
                ) 
            },
            label = { 
                Text(
                    text = "Cerrar Sesión",
                    color = Rojo,
                    fontWeight = FontWeight.Bold
                ) 
            },
            selected = false,
            onClick = {
                onLogout()
                onCloseDrawer()
            },
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}
