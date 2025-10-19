package com.proyecto.travelmarket.data.repository

import com.proyecto.travelmarket.data.DataSource
import com.proyecto.travelmarket.model.*

/**
 * Repository que maneja el acceso a los datos de TravelMarket.
 * Provee una capa de abstracción entre ViewModels y DataSource.
 *
 * Responsabilidades:
 * - Obtener listas completas de cada categoría
 * - Obtener elementos individuales por ID
 * - Proveer datos destacados
 * - Búsqueda genérica por tipo e ID
 */
class TravelRepository {

    // ========== LUGARES ==========

    /**
     * Obtiene la lista completa de lugares turísticos
     * @return List<Lugar> - Lista de todos los lugares disponibles
     */
    fun getLugares(): List<Lugar> {
        return DataSource.lugares
    }

    /**
     * Busca un lugar específico por su ID
     * @param id - ID del lugar a buscar
     * @return Lugar? - El lugar encontrado o null si no existe
     */
    fun getLugarById(id: Int): Lugar? {
        return DataSource.lugares.find { it.id == id }
    }

    // ========== EVENTOS ==========

    /**
     * Obtiene la lista completa de eventos deportivos
     * @return List<Evento> - Lista de todos los eventos disponibles
     */
    fun getEventos(): List<Evento> {
        return DataSource.eventos
    }

    /**
     * Busca un evento específico por su ID
     * @param id - ID del evento a buscar
     * @return Evento? - El evento encontrado o null si no existe
     */
    fun getEventoById(id: Int): Evento? {
        return DataSource.eventos.find { it.id == id }
    }

    // ========== RESTAURANTES ==========

    /**
     * Obtiene la lista completa de restaurantes
     * @return List<Restaurante> - Lista de todos los restaurantes disponibles
     */
    fun getRestaurantes(): List<Restaurante> {
        return DataSource.restaurantes
    }

    /**
     * Busca un restaurante específico por su ID
     * @param id - ID del restaurante a buscar
     * @return Restaurante? - El restaurante encontrado o null si no existe
     */
    fun getRestauranteById(id: Int): Restaurante? {
        return DataSource.restaurantes.find { it.id == id }
    }

    // ========== TRANSPORTES ==========

    /**
     * Obtiene la lista completa de opciones de transporte
     * @return List<Transporte> - Lista de todas las opciones de transporte
     */
    fun getTransportes(): List<Transporte> {
        return DataSource.transportes
    }

    /**
     * Busca una opción de transporte específica por su ID
     * @param id - ID del transporte a buscar
     * @return Transporte? - El transporte encontrado o null si no existe
     */
    fun getTransporteById(id: Int): Transporte? {
        return DataSource.transportes.find { it.id == id }
    }

    // ========== DESTACADOS ==========

    /**
     * Obtiene una lista de elementos destacados de todas las categorías
     * @return List<Any> - Lista mixta de elementos destacados
     */
    fun getDestacados(): List<Any> {
        return DataSource.getDestacados()
    }

    // ========== BÚSQUEDA GENÉRICA ==========

    /**
     * Busca un elemento por tipo e ID de forma genérica
     * Útil para la pantalla de detalle que recibe parámetros dinámicos
     *
     * @param tipo - Tipo de elemento: "lugar", "evento", "restaurante", "transporte"
     * @param id - ID del elemento a buscar
     * @return Any? - El elemento encontrado o null si no existe o tipo inválido
     */
    fun getItemByTypeAndId(tipo: String, id: Int): Any? {
        return when (tipo.lowercase()) {
            "lugar" -> getLugarById(id)
            "evento" -> getEventoById(id)
            "restaurante" -> getRestauranteById(id)
            "transporte" -> getTransporteById(id)
            else -> null
        }
    }

    // ========== MÉTODOS ADICIONALES (para futuras expansiones) ==========

    /**
     * Filtra lugares por categoría
     * @param categoria - Categoría a filtrar
     * @return List<Lugar> - Lugares que coinciden con la categoría
     */
    fun getLugaresByCategoria(categoria: String): List<Lugar> {
        return DataSource.lugares.filter { it.categoria == categoria }
    }

    /**
     * Filtra eventos por deporte
     * @param deporte - Deporte a filtrar
     * @return List<Evento> - Eventos que coinciden con el deporte
     */
    fun getEventosByDeporte(deporte: String): List<Evento> {
        return DataSource.eventos.filter { it.deporte == deporte }
    }

    /**
     * Filtra restaurantes por tipo de cocina
     * @param tipoCocina - Tipo de cocina a filtrar
     * @return List<Restaurante> - Restaurantes que coinciden con el tipo
     */
    fun getRestaurantesByTipoCocina(tipoCocina: String): List<Restaurante> {
        return DataSource.restaurantes.filter { it.tipoCocina == tipoCocina }
    }

    /**
     * Filtra transportes por tipo
     * @param tipo - Tipo de transporte a filtrar
     * @return List<Transporte> - Transportes que coinciden con el tipo
     */
    fun getTransportesByTipo(tipo: String): List<Transporte> {
        return DataSource.transportes.filter { it.tipo == tipo }
    }
}