package com.example.myapplication.domain.usecases.videojuegos

import com.example.myapplication.domain.data.RepositorioVideojuegos

class DeleteVideojuegoUseCase {
    operator fun invoke(index: Int): Boolean {
        return RepositorioVideojuegos.removeJuego(index)
    }
}

