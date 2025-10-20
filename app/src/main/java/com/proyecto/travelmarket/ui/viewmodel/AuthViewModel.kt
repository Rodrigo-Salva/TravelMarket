package com.proyecto.travelmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.travelmarket.data.repository.UserRepository
import com.proyecto.travelmarket.model.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState
    
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun register(nombre: String, usuario: String, password: String) {
        viewModelScope.launch {
            // Se crea el usuario con la contraseña en texto plano (para esta simulación)
            repository.register(User(nombre = nombre, usuario = usuario, password = password))
        }
    }

    fun login(usuario: String, password: String) {
        viewModelScope.launch {
            // 1. Buscar el usuario SOLO por el nombre de usuario
            val user = repository.getUserByUsername(usuario)

            // 2. Verificar la contraseña
            val loginSuccessful =
                if (user != null) {
                    // ¡Aquí se comprueba que la contraseña ingresada (password)
                    // coincida con la guardada en la DB (user.password)!
                    user.password == password
                } else {
                    false
                }

            _loginState.value = loginSuccessful
            
            // Si el login es exitoso, guardar el usuario actual
            if (loginSuccessful && user != null) {
                _currentUser.value = user
            }
        }
    }
    
    fun logout() {
        _currentUser.value = null
        _loginState.value = null
    }
}