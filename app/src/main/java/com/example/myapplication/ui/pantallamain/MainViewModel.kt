package com.example.myapplication.ui.pantallamain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.data.RepositorioVideojuegos
import com.example.myapplication.domain.modelo.Videojuego
import com.example.myapplication.domain.usecases.videojuegos.AddVideojuegoUseCase
import com.example.myapplication.domain.usecases.videojuegos.DeleteVideojuegoUseCase
import com.example.myapplication.domain.usecases.videojuegos.SiguienteVideojuegoUseCase

class MainViewModel : ViewModel() {
    private var _state: MutableLiveData<MainState> = MutableLiveData(MainState())
    val state: LiveData<MainState> get() = _state
    init{
        _state.value = MainState(numVideojuegos = RepositorioVideojuegos.size())
    }

    fun ClickBotonGuardar(videojuego: Videojuego) {
        val addVideojuegoUseCase = AddVideojuegoUseCase()
        if (addVideojuegoUseCase.invoke(videojuego)) {
            _state.value =
                _state.value?.copy(
                    mensaje = "Videojuego añadido",
                    videojuego = videojuego
                )
        } else {
            _state.value = _state.value?.copy(mensaje = "ERROR AL AÑADIR")
        }
    }

    fun siguienteVideojuego() {
        val indice = _state.value?.indiceVideojuego ?: -1
        val total = RepositorioVideojuegos.size()

        if (total >0) {
            var nuevoIndice = indice + 1
            if (nuevoIndice >= total) {
                nuevoIndice = 0
            }
            val videojuego = SiguienteVideojuegoUseCase().invoke(nuevoIndice)
            _state.value = _state.value?.copy(
                videojuego = videojuego,
                indiceVideojuego = nuevoIndice
            )
        }
    }

    fun anteriorVideojuego() {
        val indice = _state.value?.indiceVideojuego ?: 0
        val total = RepositorioVideojuegos.size()

        if (total > 0) {
            var nuevoIndice = indice - 1
            if (nuevoIndice < 0) {
                nuevoIndice = total - 1
            }
            val videojuego = SiguienteVideojuegoUseCase().invoke(nuevoIndice)
            _state.value = _state.value?.copy(
                videojuego = videojuego,
                indiceVideojuego = nuevoIndice
            )
        }

    }

    fun borrarVideojuego() {
        val indice = _state.value?.indiceVideojuego ?: -1

        val eliminado = DeleteVideojuegoUseCase().invoke(indice)
        if (eliminado) {
            val nuevoTotal = RepositorioVideojuegos.size()
            if (nuevoTotal == 0) {

                _state.value = _state.value?.copy(
                    mensaje = "Videojuego eliminado",
                    numVideojuegos = nuevoTotal,
                    indiceVideojuego = -1,
                    videojuego = Videojuego()
                )
            } else {

                var nuevoIndice = indice
                if (nuevoIndice >= nuevoTotal) {
                    nuevoIndice = 0
                }
                val vj = SiguienteVideojuegoUseCase().invoke(nuevoIndice)
                _state.value = _state.value?.copy(
                    mensaje = "Videojuego eliminado",
                    numVideojuegos = nuevoTotal,
                    indiceVideojuego = nuevoIndice,
                    videojuego = vj
                )
            }
        }
    }

    fun limpiarMensaje() {
        _state.value = _state.value?.copy(mensaje = null)
    }

    fun limpiarCampos() {
        _state.value = _state.value?.copy(
            videojuego = Videojuego(),
            indiceVideojuego = -1,
            mensaje = null
        )
    }
}


class MainViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(

            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}