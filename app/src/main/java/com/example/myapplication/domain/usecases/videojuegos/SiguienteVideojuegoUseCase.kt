package com.example.myapplication.domain.usecases.videojuegos

import com.example.myapplication.domain.data.RepositorioVideojuegos
import com.example.myapplication.domain.modelo.Videojuego

class SiguienteVideojuegoUseCase {
    operator fun invoke(id:Int): Videojuego = RepositorioVideojuegos.getVideojuego(id)
}