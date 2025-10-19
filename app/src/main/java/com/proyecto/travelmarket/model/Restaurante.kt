package com.proyecto.travelmarket.model

data class Restaurante(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val descripcionDetallada: String,
    val tipoCocina: String,
    val ubicacion: String,
    val precio: Int,
    val rating: Double,
    val imagen: String,
    val especialidad: String,
    val horario: String = "11:00 - 23:00",
    val destacado: Boolean = false
)
