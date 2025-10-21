package com.proyecto.travelmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class FavoritoItem(
    val id: Int,
    val tipo: String
)

class FavoritosViewModel : ViewModel() {
    private val _favoritos: SnapshotStateList<FavoritoItem> = mutableStateListOf()
    val favoritos: List<FavoritoItem> get() = _favoritos

    fun agregarFavorito(id: Int, tipo: String) {
        val item = FavoritoItem(id, tipo)
        if (!_favoritos.contains(item)) {
            _favoritos.add(item)
        }
    }

    fun eliminarFavorito(id: Int, tipo: String) {
        _favoritos.removeAll { it.id == id && it.tipo == tipo }
    }

    fun esFavorito(id: Int, tipo: String): Boolean {
        return _favoritos.any { it.id == id && it.tipo == tipo }
    }

    fun toggleFavorito(id: Int, tipo: String) {
        if (esFavorito(id, tipo)) {
            eliminarFavorito(id, tipo)
        } else {
            agregarFavorito(id, tipo)
        }
    }
}
