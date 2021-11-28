package es.murallaromana.pmdm.activities

import android.R
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
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
        setTitle("Detalles de: "+pelicula.titulo)


        binding.txtDetalleTitulo.setText(pelicula.titulo)
        binding.txtDetalleAutor.setText(pelicula.autor)
        binding.txtDetalleGenero.setText(pelicula.genero)
        binding.txtDetalleNota.setText(pelicula.nota)
        binding.txtDetalleTelefono.setText(pelicula.telefono)
        binding.txtDetalleResumen.setText(pelicula.resumen)

        Picasso.get().load(pelicula.url).into(binding.DetalleImagen)

        binding.txtDetalleTelefono.setOnClickListener(){
            val telefono = binding.txtDetalleTelefono.text.toString()
            val llamada = Intent(Intent.ACTION_CALL);
            intent.data = Uri.parse(telefono)
            startActivity(llamada)
        }

    }
}