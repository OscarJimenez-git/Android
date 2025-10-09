package com.example.myapplication.ui.pantallamain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.modelo.Videojuego
import com.example.myapplication.domain.usecases.videojuegos.addVideojuegoUseCase

class MainViewModel: ViewModel() {
    private var _state: MutableLiveData<MainState> = MutableLiveData(MainState())
    val state: LiveData<MainState> get() = _state

    fun ClickBotonGuardar(videojuego: Videojuego) {
        val addVideojuegoUseCase = addVideojuegoUseCase()
        if (addVideojuegoUseCase.invoke(videojuego)) {
            _state.value = _state.value?.copy(mensaje = "Videojuego añadido", videojuego = videojuego)
        }
        else {
            _state.value = _state.value?.copy(mensaje = "ERROR AL AÑADIR")
        }
    }

    fun limpiarMensaje() {
        _state.value = _state.value?.copy(mensaje = null)
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