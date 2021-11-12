package es.murallaromana.pmdm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import es.murallaromana.pmdm.R
import es.murallaromana.pmdm.databinding.ActivityDetalleBinding
import es.murallaromana.pmdm.databinding.ActivityListaMain3Binding
import es.murallaromana.pmdm.model.entidades.Pelicula

class detalleActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pelicula = intent.extras?.get("pelicula")as Pelicula

        binding.txtDetalleTitulo.setText(pelicula.titulo)
        Picasso.get().load(pelicula.url).into(binding.DetalleImagen)

    }
}