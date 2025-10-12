package com.example.myapplication.ui.pantallamain

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.modelo.Videojuego

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        eventos()
        observacion()
    }
    private fun observacion(){
        viewModel.state.observe(this){state ->
            binding.tituloEditText?.setText(state.videojuego.titulo)
            binding.desarrolladorEditText?.setText(state.videojuego.desarrollador)
            binding.generoEditText?.setText(state.videojuego.genero)


            state.mensaje?.let { error ->
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                viewModel.limpiarMensaje()
            }

        }
    }
    private fun eventos(){
        binding.guardarButton.setOnClickListener {
            var videojuego= Videojuego(
                binding.tituloEditText?.text.toString(),
                binding.desarrolladorEditText?.text.toString(),
                binding.generoEditText?.text.toString()

            )
            viewModel.ClickBotonGuardar(videojuego)
        }
        binding.siguienteButton.setOnClickListener {
            viewModel.siguienteVideojuego()
        }
        binding.anteriorButton.setOnClickListener {
            viewModel.anteriorVideojuego()
        }
        binding.borrarButton.setOnClickListener {
            viewModel.borrarVideojuego()
        }

    }
}
