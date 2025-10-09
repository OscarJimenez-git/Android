package com.example.myapplication.domain.data

import com.example.myapplication.domain.modelo.Videojuego

object RepositorioVideojuegos {
    private val videojuegos =mutableListOf<Videojuego>()

    init{
        videojuegos.add(Videojuego("The Legend of Zelda: Breath of the Wild", "Nintendo", "Action-adventure"))
    }

    fun getVideojuego(id:Int) = videojuegos[id]

    fun addVideojuego(videojuego: Videojuego) = videojuegos.add(videojuego)
}