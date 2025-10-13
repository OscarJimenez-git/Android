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
import com.example.myapplication.domain.data.RadioOpcion

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

            val idRadio = when (state.videojuego.opcionRadio) {
                RadioOpcion.OPCION1 -> R.id.radio_SinEmpezar
                RadioOpcion.OPCION2 -> R.id.radio_Jugando
                RadioOpcion.OPCION3 -> R.id.radio_Completado
            }
            binding.estadoGroup.check(idRadio)

            binding.onlineCheckBox.isChecked = state.videojuego.marcado

            binding.comentariosEditText?.setText(state.videojuego.comentarios)


            state.mensaje?.let { error ->
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                viewModel.limpiarMensaje()
            }

        }
    }
    private fun eventos(){


        binding.guardarButton.setOnClickListener {
            // obtener opcion seleccionada del RadioGroup
            val opcionSeleccionada = when (binding.estadoGroup.checkedRadioButtonId) {
                R.id.radio_SinEmpezar -> RadioOpcion.OPCION1
                R.id.radio_Jugando -> RadioOpcion.OPCION2
                R.id.radio_Completado -> RadioOpcion.OPCION3
                else -> RadioOpcion.OPCION1
            }


            val videojuego= Videojuego(
                titulo = binding.tituloEditText?.text.toString(),
                desarrollador = binding.desarrolladorEditText?.text.toString(),
                genero = binding.generoEditText?.text.toString(),
                opcionRadio = opcionSeleccionada,
                marcado = binding.onlineCheckBox.isChecked,
                comentarios = binding.comentariosEditText?.text.toString()

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
        binding.limpiarButton.setOnClickListener {
            viewModel.limpiarCampos()
        }

    }
}
