package com.proyecto.travelmarket.utils

import com.proyecto.travelmarket.R

object ImageUtils {
    fun getImageRes(imagen: String): Int {
        return when (imagen) {
            "machu_picchu" -> R.drawable.machu_picchu
            "malecon_miraflores" -> R.drawable.malecon_miraflores
            "plaza_mayor" -> R.drawable.plaza_mayor
            else -> R.drawable.ic_launcher_background
        }
    }

    fun getImageResRest(imagen: String): Int {
        return when (imagen) {
            "la_granja_azul" -> R.drawable.la_granja_azul
            "maido" -> R.drawable.maido
            "central" -> R.drawable.central
            else -> R.drawable.ic_launcher_background
        }
    }

    fun getImageResTrans(imagen: String): Int {
        return when (imagen) {
            "latam" -> R.drawable.latam
            "metropolitano" -> R.drawable.metropolitano
            "mirabus" -> R.drawable.mirabus
            else -> R.drawable.ic_launcher_background
        }
    }

    fun getImageResEvent(imagen: String): Int {
        return when (imagen) {
            "atletismo" -> R.drawable.atletismo
            "ciclismo" -> R.drawable.ciclismo
            "voley" -> R.drawable.voley
            else -> R.drawable.ic_launcher_background
        }
    }
}
