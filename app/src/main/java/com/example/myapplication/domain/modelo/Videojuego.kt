package com.example.myapplication.domain.modelo

import com.example.myapplication.domain.data.RadioOpcion


data class Videojuego(
    val titulo: String = "",
    val desarrollador: String = "",
    val genero: String = "",
    val opcionRadio: RadioOpcion = RadioOpcion.OPCION1,
    val marcado: Boolean = false,
    val comentarios: String = ""
)
