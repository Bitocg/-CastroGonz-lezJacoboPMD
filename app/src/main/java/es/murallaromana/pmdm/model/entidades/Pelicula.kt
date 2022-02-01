package es.murallaromana.pmdm.model.entidades

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Pelicula (
    @SerializedName("title")
    var titulo: String,
    var autor: String,
    var genero: String,
    var nota: String,
    var telefono: String,
    var url: String,
    var resumen: String,
        ):Serializable


