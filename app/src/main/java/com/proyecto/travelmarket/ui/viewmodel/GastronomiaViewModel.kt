package com.proyecto.travelmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.travelmarket.data.repository.TravelRepository
import com.proyecto.travelmarket.model.Restaurante
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de Gastronomía.
 * Gestiona el estado de la lista de restaurantes.
 */
class GastronomiaViewModel(
    private val repository: TravelRepository = TravelRepository()
) : ViewModel() {

    private val _restaurantes = MutableStateFlow<List<Restaurante>>(emptyList())
    val restaurantes: StateFlow<List<Restaurante>> = _restaurantes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadRestaurantes()
    }

    private fun loadRestaurantes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _restaurantes.value = repository.getRestaurantes()
            } catch (e: Exception) {
                _restaurantes.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Filtra restaurantes por tipo de cocina
     */
    fun filterByTipoCocina(tipoCocina: String) {
        viewModelScope.launch {
            _restaurantes.value = repository.getRestaurantesByTipoCocina(tipoCocina)
        }
    }

    /**
     * Restaura la lista completa
     */
    fun clearFilter() {
        loadRestaurantes()
    }
}