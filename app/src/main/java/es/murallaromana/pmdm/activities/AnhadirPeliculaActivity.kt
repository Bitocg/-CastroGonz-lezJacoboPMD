package es.murallaromana.pmdm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import es.murallaromana.pmdm.R
import es.murallaromana.pmdm.databinding.ActivityAnhadirPeliculaBinding
import es.murallaromana.pmdm.databinding.ActivityDetalleBinding


import es.murallaromana.pmdm.model.entidades.Pelicula

class AnhadirPeliculaActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityAnhadirPeliculaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAnhadirPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Añadir película")
        val pelicula = intent.extras?.get("pelicula")as Pelicula

        if(pelicula!=null) {
            setTitle("Editar película")
            binding.txtTituloA.setText(pelicula.titulo)
            binding.txtAutorA.setText(pelicula.autor)
            binding.txtGeneroA.setText(pelicula.genero)
            binding.txtNotaA.setText(pelicula.nota)
            binding.txtTelefonoA.setText(pelicula.telefono)
            binding.txtUrlA.setText(pelicula.url)
            binding.txtResumenA.setText(pelicula.resumen)
        }else{
            setTitle("Añadir película")
            binding.txtTituloA.setText(pelicula.titulo)
            binding.txtAutorA.setText(pelicula.autor)
            binding.txtGeneroA.setText(pelicula.genero)
            binding.txtNotaA.setText(pelicula.nota)
            binding.txtTelefonoA.setText(pelicula.telefono)
            binding.txtUrlA.setText(pelicula.url)
            binding.txtResumenA.setText(pelicula.resumen)
        }
    }


}