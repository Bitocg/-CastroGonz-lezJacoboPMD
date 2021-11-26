package es.murallaromana.pmdm

import android.app.Application
import es.murallaromana.pmdm.model.dao.PeliculasDaoMocklmpl
import es.murallaromana.pmdm.model.entidades.Pelicula

class App: Application() {

    companion object{
        var peliculas: MutableList<Pelicula> = ArrayList()
    }

    override fun onCreate() {
        super.onCreate()
        val dao = PeliculasDaoMocklmpl()
        peliculas = dao.getTodos()
    }
}