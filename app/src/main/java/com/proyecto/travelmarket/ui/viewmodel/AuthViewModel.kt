package com.proyecto.travelmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.travelmarket.data.repository.UserRepository
import com.proyecto.travelmarket.model.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Sealed class para manejar diferentes estados de login
sealed class LoginResult {
    object Idle : LoginResult()
    object Success : LoginResult()
    object UserNotFound : LoginResult()
    object WrongPassword : LoginResult()
}

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginResult>(LoginResult.Idle)
    val loginState: StateFlow<LoginResult> = _loginState

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun register(nombre: String, usuario: String, password: String) {
        viewModelScope.launch {
            repository.register(User(nombre = nombre, usuario = usuario, password = password))
        }
    }

    fun login(usuario: String, password: String) {
        viewModelScope.launch {
            // 1. Buscar el usuario por nombre de usuario
            val user = repository.getUserByUsername(usuario)

            // 2. Determinar el resultado del login
            _loginState.value = when {
                user == null -> LoginResult.UserNotFound // Usuario no existe
                user.password != password -> LoginResult.WrongPassword // Contraseña incorrecta
                else -> {
                    // Login exitoso - guardar usuario actual
                    _currentUser.value = user
                    LoginResult.Success
                }
            }
        }
    }

    fun logout() {
        _currentUser.value = null
        _loginState.value = LoginResult.Idle
    }

    fun resetLoginState() {
        _loginState.value = LoginResult.Idle
    }
}
