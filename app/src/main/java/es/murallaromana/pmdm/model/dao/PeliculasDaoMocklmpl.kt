package es.murallaromana.pmdm.model.dao

import es.murallaromana.pmdm.model.entidades.Pelicula

open class PeliculasDaoMocklmpl: PeliculaDao {
    override fun getTodos() = mutableListOf(
        Pelicula("El señor de los anillos", "Peter Jackson ","Fantasia ","8.8","5555555555", "https://i1.wp.com/noescinetodoloquereluce.com/wp-content/uploads/2021/05/qHEU9067PL8iIZULg7q58NvoWlS-scaled.jpg?fit=1707%2C2560&ssl=1","En la Tierra Media, el Señor Oscuro Sauron ordenó a los Elfos que forjaran los Grandes Anillos de Poder. Tres para los reyes Elfos, siete para los Señores Enanos, y nueve para los Hombres Mortales. Pero Saurón también forjó, en secreto, el Anillo Único, que tiene el poder de esclavizar toda la Tierra Media. Con la ayuda de sus amigos y de valientes aliados, el joven hobbit Frodo emprende un peligroso viaje con la misión de destruir el Anillo Único. Pero el malvado Sauron ordena la persecución del grupo, compuesto por Frodo y sus leales amigos hobbits, un mago, un hombre, un elfo y un enano. La misión es casi suicida pero necesaria, pues si Sauron con su ejército de orcos lograra recuperar el Anillo, sería el final de la Tierra Media."),
        Pelicula("Luca", "Enrico Casarosa", "Infantil", "8", "9999999","https://es.web.img3.acsta.net/pictures/21/04/29/11/03/3538359.jpg","En un hermoso pueblo en la Riviera italiana, Luca comparte sus aventuras de verano con su nuevo mejor amigo: un monstruo marino que se convierte en humano cuando está seco."),
        Pelicula("Sonic", " Jeff Fowler ", "Infantil", "8", "9999999","https://es.web.img2.acsta.net/pictures/19/11/12/12/25/0815514.jpg","Sonic intenta navegar por las complejidades de la vida en la Tierra con su nuevo mejor amigo, un humano llamado Tom Wachowski. Pronto deben unir fuerzas para evitar que el malvado Dr. Robotnik capture a Sonic y use sus poderes para dominar el mundo."),
        Pelicula("Encanto", " Byron Howard", "Infantil", "8", "9999999","https://lumiere-a.akamaihd.net/v1/images/image_adf151ff.jpeg?region=0%2C0%2C540%2C810",""),
    )
}