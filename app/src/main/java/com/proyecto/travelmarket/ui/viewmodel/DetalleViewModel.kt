package com.proyecto.travelmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.travelmarket.data.repository.TravelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de Detalle.
 * Gestiona el estado del elemento individual que se está mostrando.
 * Soporta carga dinámica basada en tipo e ID.
 */
class DetalleViewModel(
    private val repository: TravelRepository = TravelRepository()
) : ViewModel() {

    // Estado del item actual (puede ser Lugar, Evento, Restaurante o Transporte)
    private val _detalleItem = MutableStateFlow<Any?>(null)
    val detalleItem: StateFlow<Any?> = _detalleItem.asStateFlow()

    // Estado de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Estado de error
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    /**
     * Carga el detalle de un elemento específico
     * @param tipo - Tipo de elemento: "lugar", "evento", "restaurante", "transporte"
     * @param id - ID del elemento a cargar
     */
    fun loadDetalle(tipo: String, id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val item = repository.getItemByTypeAndId(tipo, id)

                if (item != null) {
                    _detalleItem.value = item
                } else {
                    _error.value = "No se encontró el elemento solicitado"
                }
            } catch (e: Exception) {
                _error.value = "Error al cargar los datos: ${e.message}"
                _detalleItem.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Limpia el estado actual
     */
    fun clearDetalle() {
        _detalleItem.value = null
        _error.value = null
    }
}