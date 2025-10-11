package com.example.myapplication.ui.pantallamain

import com.example.myapplication.domain.modelo.Videojuego

data class MainState (
    val numVideojuegos : Int = 0,
    val indiceVideojuego: Int = -1,
    val videojuego: Videojuego =Videojuego(),
    val mensaje :String?="",
)