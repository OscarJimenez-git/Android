package com.example.myapplication.ui.pantallamain

import com.example.myapplication.domain.modelo.Videojuego

data class MainState (
    val videojuego: Videojuego =Videojuego(),
    val mensaje :String?="",
)