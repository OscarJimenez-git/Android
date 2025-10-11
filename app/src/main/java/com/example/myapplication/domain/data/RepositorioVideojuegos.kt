package com.example.myapplication.domain.data

import com.example.myapplication.domain.modelo.Videojuego

object RepositorioVideojuegos {
    private val videojuegos =mutableListOf<Videojuego>()

    init{
        videojuegos.add(Videojuego("The Legend of Zelda: Breath of the Wild", "Nintendo", "Action-adventure"))
        videojuegos.add(Videojuego("God of War", "Santa Monica Studio", "Action-adventure"))
        videojuegos.add(Videojuego("Red Dead Redemption 2", "Rockstar Games", "Action-adventure"))
        videojuegos.add(Videojuego("The Witcher 3: Wild Hunt", "CD Projekt Red", "Action RPG"))
    }

    fun size() = videojuegos.size
    val numVideojuegos: Int
        get() = videojuegos.size
    fun getVideojuego(id:Int) = videojuegos[id]

    fun addVideojuego(videojuego: Videojuego) = videojuegos.add(videojuego)
}