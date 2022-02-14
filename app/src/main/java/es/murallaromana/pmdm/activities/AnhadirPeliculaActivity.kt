package es.murallaromana.pmdm.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.squareup.picasso.Picasso
import es.murallaromana.pmdm.App
import es.murallaromana.pmdm.R
import es.murallaromana.pmdm.databinding.ActivityAnhadirPeliculaBinding

import es.murallaromana.pmdm.databinding.ActivityDetalleBinding
import es.murallaromana.pmdm.model.dao.PeliculaDao
import es.murallaromana.pmdm.model.dao.PeliculasDaoMocklmpl
import es.murallaromana.pmdm.model.dao.SharedPreference
import es.murallaromana.pmdm.model.dao.retrofit.RetrofitCliente
import es.murallaromana.pmdm.model.entidades.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnhadirPeliculaActivity: AppCompatActivity() {
    private  lateinit var binding: ActivityAnhadirPeliculaBinding
    private lateinit var preferences: SharedPreference
    private  lateinit  var pelicula :  Pelicula
    val context = this
    var id : String ? = null
    var token : String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val preferences = SharedPreference(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityAnhadirPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        token = preferences.recogerToken()
        id = intent.extras?.get("id") as String?
        val call = RetrofitCliente.API_RETROFIT.getId("Bearer" + token, id)
        call.enqueue(object : Callback<Pelicula> {
            override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                Log.d("respuesta: onFailure", t.toString())
                setTitle("Añadir película")
            }
            override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                pelicula = Pelicula(
                    response.body()?.id.toString(),
                    response.body()?.titulo.toString(),
                    response.body()?.autor.toString(),
                    response.body()?.genero.toString(),
                    response.body()?.nota.toString(),
                    response.body()?.telefono.toString(),
                    response.body()?.url.toString(),
                    response.body()?.resumen.toString()
                )
                    setTitle("Editar o borrar película")
                    binding.txtTituloA.setText(pelicula.titulo)
                    binding.txtAutorA.setText(pelicula.autor)
                    binding.txtGeneroA.setText(pelicula.genero)
                    binding.txtNotaA.setText(pelicula.nota.toString())
                    binding.txtTelefonoA.setText(pelicula.telefono.toString())
                    binding.txtResumenA.setText(pelicula.resumen)
                    binding.txtUrlA.setText(pelicula.url)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val preferences = SharedPreference(applicationContext)
        if(id==null){
            if (item.itemId == R.id.action_delete) {
                // Si pulsas en la papelera vacía los txt
                binding.txtAutorA.setText("")
                binding.txtTituloA.setText("")
                binding.txtGeneroA.setText("")
                binding.txtNotaA.setText("")
                binding.txtTelefonoA.setText("")
                binding.txtUrlA.setText("")
                binding.txtResumenA.setText("")
                Toast.makeText(context, "Campos borrados", Toast.LENGTH_SHORT).show()
            } else if (item.itemId == R.id.action_save_or_update){
                // Si pulsas en el check crea la peli
                if(TextUtils.equals(binding.txtAutorA.text.toString(),"") || TextUtils.equals(binding.txtTituloA.text.toString(),"")
                    || TextUtils.equals(binding.txtGeneroA.text.toString(),"") || TextUtils.equals(binding.txtNotaA.text.toString(),"")
                    || TextUtils.equals(binding.txtTelefonoA.text.toString(),"") || TextUtils.equals(binding.txtUrlA.text.toString(),"")
                    || TextUtils.equals(binding.txtResumenA.text.toString(),""))
                {
                    Toast.makeText(context, "Hay campos vacíos",Toast.LENGTH_SHORT).show()
                }else {
                    val preferences = SharedPreference(applicationContext)
                    val token = preferences.recogerToken()
                    val pelicula = Pelicula(
                        null,
                        binding.txtTituloA.text.toString(),
                        binding.txtAutorA.text.toString(),
                        binding.txtGeneroA.text.toString(),
                        binding.txtNotaA.text.toString(),
                        binding.txtTelefonoA.text.toString(),
                        binding.txtUrlA.text.toString(),
                        binding.txtResumenA.text.toString()
                    )
                    val call = RetrofitCliente.API_RETROFIT.crear("Bearer" + token, pelicula)
                    call.enqueue(object : Callback<Unit> {
                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("respuesta: onFailure", t.toString())
                        }

                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.code() > 299 || response.code() < 200) {
                                Toast.makeText(
                                    context,
                                    "No se pudo agregar",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Se creo la pelicula",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            }
                        }
                    })
                }
            }
        }
        else {
            setTitle("Editar o borrar película")
            if (item.itemId == R.id.action_delete) {
                //si pulsas en la papelera borra la peli
                val mensaje = AlertDialog.Builder(context)
                mensaje.setTitle("Borrar pelicula")
                mensaje.setMessage("Seguro que quieres borrarla:")
                mensaje.setPositiveButton("Si") { dialogInterface, i ->
                    val call = RetrofitCliente.API_RETROFIT.delete("Bearer" + token, id)
                    call.enqueue(object : Callback<Pelicula> {
                        override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                            Log.d("respuesta: onFailure", t.toString())
                        }
                        override fun onResponse(
                            call: Call<Pelicula>,
                            response: Response<Pelicula>
                        ) {
                            if (response.code() > 299 || response.code() < 200) {
                                Toast.makeText(
                                    context,
                                    "La pelicula no pudo borrarse",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (response.code() == 401 || response.code() == 500) {
                                Toast.makeText(
                                    context,
                                    "Inicio de sesión caducado",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(context, "Película borrada", Toast.LENGTH_SHORT)
                                    .show()
                                finish()
                            }
                        }
                    })
                }
                mensaje.setNegativeButton("No") { dialogInterface, i ->
                }
                mensaje.show()
            } else if (item.itemId == R.id.action_save_or_update) {
                //si pulsas en el check edita la peli
                if(TextUtils.equals(binding.txtAutorA.text.toString(),"") || TextUtils.equals(binding.txtTituloA.text.toString(),"")
                    || TextUtils.equals(binding.txtGeneroA.text.toString(),"") || TextUtils.equals(binding.txtNotaA.text.toString(),"")
                    || TextUtils.equals(binding.txtTelefonoA.text.toString(),"") || TextUtils.equals(binding.txtUrlA.text.toString(),"")
                    || TextUtils.equals(binding.txtResumenA.text.toString(),""))
                {
                    Toast.makeText(context, "Hay campos vacíos", Toast.LENGTH_SHORT).show()
                } else {
                    val token = preferences.recogerToken()
                    val pelicula2 = Pelicula(
                        id,
                        binding.txtTituloA.text.toString(),
                        binding.txtAutorA.text.toString(),
                        binding.txtGeneroA.text.toString(),
                        binding.txtNotaA.text.toString(),
                        binding.txtTelefonoA.text.toString(),
                        binding.txtUrlA.text.toString(),
                        binding.txtResumenA.text.toString()
                    )
                    val call = RetrofitCliente.API_RETROFIT.editar("Bearer" + token, pelicula2)
                    call.enqueue(object : Callback<Pelicula> {
                        override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                            Log.d("respuesta: onFailure", t.toString())
                        }
                        override fun onResponse(
                            call: Call<Pelicula>,
                            response: Response<Pelicula>
                        ) {
                            if (response.code() > 299 || response.code() < 200) {
                                Toast.makeText(
                                    context,
                                    "La pelicula no pudo recuperarse",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (response.code() == 401 || response.code() == 500) {
                                Toast.makeText(
                                    context,
                                    "Inicio de sesión caducado",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(context, "Película editada", Toast.LENGTH_SHORT)
                                    .show()
                                finish()
                            }
                        }
                    })
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
