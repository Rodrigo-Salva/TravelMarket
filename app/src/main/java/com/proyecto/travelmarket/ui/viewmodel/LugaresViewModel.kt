package com.proyecto.travelmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.travelmarket.data.repository.TravelRepository
import com.proyecto.travelmarket.model.Lugar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de Lugares.
 * Gestiona el estado de la lista de lugares turísticos.
 */
class LugaresViewModel(
    private val repository: TravelRepository = TravelRepository()
) : ViewModel() {

    private val _lugares = MutableStateFlow<List<Lugar>>(emptyList())
    val lugares: StateFlow<List<Lugar>> = _lugares.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadLugares()
    }

    private fun loadLugares() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _lugares.value = repository.getLugares()
            } catch (e: Exception) {
                _lugares.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Filtra lugares por categoría
     */
    fun filterByCategoria(categoria: String) {
        viewModelScope.launch {
            _lugares.value = repository.getLugaresByCategoria(categoria)
        }
    }

    /**
     * Restaura la lista completa
     */
    fun clearFilter() {
        loadLugares()
    }
}