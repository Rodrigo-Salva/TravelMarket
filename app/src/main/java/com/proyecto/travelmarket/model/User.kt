package com.proyecto.travelmarket.model // <--- ¡Asegúrate de que este sea el paquete correcto!

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val usuario: String,
    val password: String
)