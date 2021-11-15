package es.murallaromana.pmdm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import es.murallaromana.pmdm.adapters.ListaPeliculasAdapter
import es.murallaromana.pmdm.databinding.ActivityListaMain3Binding
import es.murallaromana.pmdm.model.dao.PeliculaDao
import es.murallaromana.pmdm.model.dao.PeliculasDaoMocklmpl
import es.murallaromana.pmdm.model.entidades.Pelicula

class ListaPeliculasActivity3 : AppCompatActivity() {

    private lateinit var biding3 : ActivityListaMain3Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biding3 = ActivityListaMain3Binding.inflate(layoutInflater)
        setContentView(biding3.root)

         // boton añadir
         var añadir = biding3.btAnhadir
         //cuando pulse el boton añadir
         añadir.setOnClickListener(){

         }

        // Asigno un LayoutManager vertical
        biding3.rvListaPeliculas.layoutManager=LinearLayoutManager(this)

        // Cojo la lista creada
        val peliculasdao = PeliculasDaoMocklmpl()
        val listapeliculas = peliculasdao.getTodos()

        // Le paso la lista
        val layoutManager = LinearLayoutManager(this)
        var adapter = ListaPeliculasAdapter(listapeliculas,this)

        //
        biding3.rvListaPeliculas.adapter = adapter
        biding3.rvListaPeliculas.layoutManager = layoutManager


    }


}