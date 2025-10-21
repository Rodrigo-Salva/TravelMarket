package com.proyecto.travelmarket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proyecto.travelmarket.model.User

@Dao
interface UserDao {

    // 1. Registro: Inserta el nuevo usuario.
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(user: User)

    // 2. Login (Búsqueda por nombre de usuario):
    // La nombramos `getUserByUsername` para seguir la buena práctica de seguridad.
    @Query("SELECT * FROM users WHERE usuario = :usuario LIMIT 1")
    suspend fun getUserByUsername(usuario: String): User?
}
