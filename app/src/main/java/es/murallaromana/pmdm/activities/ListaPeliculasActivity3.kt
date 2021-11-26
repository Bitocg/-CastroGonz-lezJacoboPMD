package es.murallaromana.pmdm.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import es.murallaromana.pmdm.App
import es.murallaromana.pmdm.R
import es.murallaromana.pmdm.adapters.ListaPeliculasAdapter
import es.murallaromana.pmdm.databinding.ActivityListaMain3Binding
import es.murallaromana.pmdm.model.dao.PeliculaDao
import es.murallaromana.pmdm.model.dao.PeliculasDaoMocklmpl
import es.murallaromana.pmdm.model.entidades.Pelicula

class ListaPeliculasActivity3: AppCompatActivity() {

    private lateinit var biding3 : ActivityListaMain3Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biding3 = ActivityListaMain3Binding.inflate(layoutInflater)
        setContentView(biding3.root)

         // boton añadir
         var añadir = biding3.btAnhadir

         //cuando pulse el boton añadir
         añadir.setOnClickListener(){
             val añadirPeli= Intent(this, AnhadirPeliculaActivity::class.java) //para llamar a la pantalla de detalle
             startActivity(añadirPeli)
         }
        setTitle("Lista de películas")

        // Asigno un LayoutManager vertical
        biding3.rvListaPeliculas.layoutManager=LinearLayoutManager(this)

        // Cojo la lista creada
        val listapeliculas = App.peliculas

        // Le paso la lista
        val layoutManager = LinearLayoutManager(this)
        var adapter = ListaPeliculasAdapter(listapeliculas,this,this)

        //
        biding3.rvListaPeliculas.adapter = adapter
        biding3.rvListaPeliculas.layoutManager = layoutManager

    }

    override fun onResume() {
        super.onResume()

        val adapter = ListaPeliculasAdapter(App.peliculas, this, this)
        biding3.rvListaPeliculas.adapter=adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }




}