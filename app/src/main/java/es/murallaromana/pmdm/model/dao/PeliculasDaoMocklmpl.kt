package es.murallaromana.pmdm.model.dao

import es.murallaromana.pmdm.model.entidades.Pelicula

class PeliculasDaoMocklmpl: PeliculaDao {
    override fun getTodos() = listOf(
        Pelicula("El señor de los anillos", "Pepe","fantasia","8.8","5555555555","https://as.com/meristation/imagenes/2021/07/07/noticias/1625672419_027507_1625737804_noticia_normal.jpg"),
        Pelicula("Matrix", "P", "Ciencia ficcion", "8", "9999999","aa"),
        Pelicula("Matrix", "P", "Ciencia ficcion", "8", "9999999","aa"),
        Pelicula("Matrix", "P", "Ciencia ficcion", "8", "9999999","aa"),
        Pelicula("Matrix", "P", "Ciencia ficcion", "8", "9999999","aa"),
        Pelicula("Matrix", "P", "Ciencia ficcion", "8", "9999999","aa"),
        Pelicula("Matrix", "P", "Ciencia ficcion", "8", "9999999","aa"),

    )


}