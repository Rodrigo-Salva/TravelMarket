package com.proyecto.travelmarket.model

data class Transporte(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val descripcionDetallada: String,
    val tipo: String, // "Aerolínea", "Bus", "Turístico"
    val ubicacion: String,
    val ruta: String,
    val precio: Int? = null,
    val rating: Double,
    val imagen: String,
    val horario: String,
    val destacado: Boolean = false
)
