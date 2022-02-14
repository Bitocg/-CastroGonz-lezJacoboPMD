package es.murallaromana.pmdm.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import es.murallaromana.pmdm.adapters.ListaPeliculasAdapter
import es.murallaromana.pmdm.databinding.ActivityListaMain3Binding
import es.murallaromana.pmdm.model.dao.SharedPreference
import es.murallaromana.pmdm.model.dao.retrofit.RetrofitCliente
import es.murallaromana.pmdm.model.entidades.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPeliculasActivity: AppCompatActivity() {

    private lateinit var binding : ActivityListaMain3Binding
    private lateinit var preferences: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {

        setTitle("Lista de películas")

        super.onCreate(savedInstanceState)

        binding = ActivityListaMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences= SharedPreference(applicationContext)

        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { tengoPermiso: Boolean ->
            if (!tengoPermiso) {
                ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }
        }

        requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

        // boton añadir
        var añadir = binding.btAnhadir
        añadir.setOnClickListener(){
            val añadirPeli= Intent(this, AnhadirPeliculaActivity::class.java)
            startActivity(añadirPeli)
        }
    }

    override fun onResume() {
        super.onResume()
        val context = this

        val token = preferences.recogerToken()

        val call = RetrofitCliente.API_RETROFIT.getPeliculas2("Bearer" + token) //Llamamos a Retrofit

        call.enqueue(object : Callback<List<Pelicula>> {
            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("respuesta: onFailure", t.toString())
            }

            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>
            ) {
                if (response.code() > 299 || response.code() < 200) {
                    val adb = AlertDialog.Builder(context)
                    adb.setTitle("Lista de peliculas")
                    adb.setMessage("La lista de películas no pudo cargarse correctamente")
                    adb.setPositiveButton("Aceptar") { dialog, which -> }
                    adb.show()
                } else {
                    val listaPelicula: List<Pelicula>? = response.body()
                    val layoutManager = LinearLayoutManager(context)
                    val adapter = ListaPeliculasAdapter(
                        listaPelicula as MutableList<Pelicula>,
                        context,
                        this@ListaPeliculasActivity
                    )
                    binding.rvListaPeliculas.adapter = adapter
                    binding.rvListaPeliculas.layoutManager = layoutManager
                }
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }




}