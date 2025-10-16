package com.example.myapplication.domain.usecases.videojuegos

import com.example.myapplication.domain.data.RepositorioVideojuegos
import com.example.myapplication.domain.modelo.Videojuego

class GetVideojuegosUseCase {
    operator fun invoke() :List<Videojuego> = RepositorioVideojuegos.getVideojuegos()
}