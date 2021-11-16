package es.murallaromana.pmdm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import es.murallaromana.pmdm.databinding.ActivityDetalleBinding
import es.murallaromana.pmdm.model.entidades.Pelicula

class detalleActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pelicula = intent.extras?.get("pelicula")as Pelicula
        setTitle("Detalle de :"+pelicula.titulo)


        binding.txtDetalleTitulo.setText("Título: "+pelicula.titulo)
        binding.txtDetalleAutor.setText("Autor: "+pelicula.autor)
        binding.txtDetalleGenero.setText("Género: "+pelicula.genero)
        binding.txtDetalleNota.setText("Nota: "+pelicula.nota)
        binding.txtDetalleTelefono.setText("Teléfono: "+pelicula.telefono)
        binding.txtDetalleResumen.setText("Resumen: "+pelicula.resumen)

        Picasso.get().load(pelicula.url).into(binding.DetalleImagen)
    }
}