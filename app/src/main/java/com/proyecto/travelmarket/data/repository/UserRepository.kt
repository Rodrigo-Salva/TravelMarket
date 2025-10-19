package com.proyecto.travelmarket.data.repository

import com.proyecto.travelmarket.data.dao.UserDao
import com.proyecto.travelmarket.model.User

class UserRepository(private val dao: UserDao) {
    suspend fun register(user: User) = dao.register(user)

    // 💡 CAMBIO CLAVE: Usamos la función del DAO que busca solo por usuario.
    suspend fun getUserByUsername(usuario: String) = dao.getUserByUsername(usuario)
}
