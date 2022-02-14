package es.murallaromana.pmdm.activities

import android.R
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import com.squareup.picasso.Picasso
import es.murallaromana.pmdm.databinding.ActivityDetalleBinding
import es.murallaromana.pmdm.model.dao.SharedPreference
import es.murallaromana.pmdm.model.dao.retrofit.RetrofitCliente
import es.murallaromana.pmdm.model.entidades.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalleActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDetalleBinding
    private  lateinit  var pelicula :  Pelicula
    override fun onCreate(savedInstanceState: Bundle?) {

        val preferences = SharedPreference(applicationContext)
        super.onCreate(savedInstanceState)
        binding= ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = preferences.recogerToken()
        val id = intent.extras?.get("id") as String?
        val call = RetrofitCliente.API_RETROFIT.getId("Bearer" + token, id)
        call.enqueue(object : Callback<Pelicula> {
            override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                Log.d("respuesta: onFailure", t.toString())
            }
            override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                pelicula = Pelicula(
                    null,
                    response.body()?.titulo.toString(),
                    response.body()?.autor.toString(),
                    response.body()?.genero.toString(),
                    response.body()?.nota.toString(),
                    response.body()?.telefono.toString(),
                    response.body()?.url.toString(),
                    response.body()?.resumen.toString()
                )
                setTitle("Detalles de: "+pelicula.titulo)
                binding.txtDetalleTitulo.setText(pelicula.titulo)
                binding.txtDetalleAutor.setText(pelicula.autor)
                binding.txtDetalleGenero.setText(pelicula.genero)
                binding.txtDetalleNota.setText(pelicula.nota.toString())
                binding.txtDetalleTelefono.setText(pelicula.telefono.toString())
                binding.txtDetalleResumen.setText(pelicula.resumen)
                Picasso.get().load(pelicula.url).into(binding.DetalleImagen)
            }
        })

        binding.txtDetalleTelefono.setOnClickListener(){
            val telefono = binding.txtDetalleTelefono.text.toString()
            val llamada = Intent(Intent.ACTION_CALL);
            intent.data = Uri.parse(telefono)
            startActivity(llamada)
        }

    }
}