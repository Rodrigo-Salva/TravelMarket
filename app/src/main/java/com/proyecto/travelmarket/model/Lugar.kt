package com.proyecto.travelmarket.model

data class Lugar(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val descripcionDetallada: String,
    val ubicacion: String,
    val precio: Int,
    val rating: Double,
    val imagen: String,
    val categoria: String = "Turístico",
    val horario: String = "24 horas",
    val destacado: Boolean = false
)
