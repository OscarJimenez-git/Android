package com.example.myapplication.ui.listadojuegos

import com.example.myapplication.domain.modelo.Videojuego

data class ListadoVideojuegosState {
    val videojuegos : List<Videojuego>= emptyList()
    val mensaje:String?=null
}