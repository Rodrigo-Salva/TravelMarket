package com.proyecto.travelmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.travelmarket.data.repository.TravelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla Home.
 * Gestiona el estado de los elementos destacados.
 */
class HomeViewModel(
    private val repository: TravelRepository = TravelRepository()
) : ViewModel() {

    // Estado privado mutable
    private val _destacados = MutableStateFlow<List<Any>>(emptyList())

    // Estado público inmutable para la UI
    val destacados: StateFlow<List<Any>> = _destacados.asStateFlow()

    // Estado de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        // Cargar datos al inicializar el ViewModel
        loadDestacados()
    }

    /**
     * Carga los elementos destacados desde el repositorio
     */
    private fun loadDestacados() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _destacados.value = repository.getDestacados()
            } catch (e: Exception) {
                // Manejo de errores (para futuras implementaciones con API)
                _destacados.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Permite refrescar los datos manualmente
     */
    fun refresh() {
        loadDestacados()
    }
}