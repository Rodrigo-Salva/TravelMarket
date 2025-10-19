package com.proyecto.travelmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.travelmarket.data.repository.TravelRepository
import com.proyecto.travelmarket.model.Evento
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de Eventos.
 * Gestiona el estado de la lista de eventos deportivos.
 */
class EventosViewModel(
    private val repository: TravelRepository = TravelRepository()
) : ViewModel() {

    private val _eventos = MutableStateFlow<List<Evento>>(emptyList())
    val eventos: StateFlow<List<Evento>> = _eventos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadEventos()
    }

    private fun loadEventos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _eventos.value = repository.getEventos()
            } catch (e: Exception) {
                _eventos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Filtra eventos por deporte
     */
    fun filterByDeporte(deporte: String) {
        viewModelScope.launch {
            _eventos.value = repository.getEventosByDeporte(deporte)
        }
    }

    /**
     * Restaura la lista completa
     */
    fun clearFilter() {
        loadEventos()
    }
}