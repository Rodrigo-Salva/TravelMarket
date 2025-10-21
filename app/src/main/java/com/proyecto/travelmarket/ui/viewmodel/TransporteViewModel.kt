package com.proyecto.travelmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.travelmarket.data.repository.TravelRepository
import com.proyecto.travelmarket.model.Transporte
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de Transporte.
 * Gestiona el estado de la lista de opciones de transporte.
 */
class TransporteViewModel(
    private val repository: TravelRepository = TravelRepository()
) : ViewModel() {

    private val _transportes = MutableStateFlow<List<Transporte>>(emptyList())
    val transportes: StateFlow<List<Transporte>> = _transportes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadTransportes()
    }

    private fun loadTransportes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _transportes.value = repository.getTransportes()
            } catch (e: Exception) {
                _transportes.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Filtra transportes por tipo
     */
    fun filterByTipo(tipo: String) {
        viewModelScope.launch {
            _transportes.value = repository.getTransportesByTipo(tipo)
        }
    }

    /**
     * Restaura la lista completa
     */
    fun clearFilter() {
        loadTransportes()
    }
}