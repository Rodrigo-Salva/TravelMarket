package com.proyecto.travelmarket.model

data class Evento(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val descripcionDetallada: String,
    val deporte: String,
    val ubicacion: String,
    val estadio: String,
    val precio: Int,
    val rating: Double,
    val imagen: String,
    val fecha: String,
    val hora: String,
    val destacado: Boolean = false
)
