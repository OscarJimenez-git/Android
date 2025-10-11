package com.example.myapplication.domain.usecases.videojuegos

import com.example.myapplication.domain.data.RepositorioVideojuegos
import com.example.myapplication.domain.modelo.Videojuego

class AddVideojuegoUseCase {
    operator fun invoke(videojuego: Videojuego): Boolean {
        return RepositorioVideojuegos.addVideojuego(videojuego)
    }
}