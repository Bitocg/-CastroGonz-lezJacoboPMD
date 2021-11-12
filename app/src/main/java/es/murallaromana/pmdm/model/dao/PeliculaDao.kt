package es.murallaromana.pmdm.model.dao

import es.murallaromana.pmdm.model.entidades.Pelicula

interface PeliculaDao {
    fun getTodos(): List<Pelicula>
}