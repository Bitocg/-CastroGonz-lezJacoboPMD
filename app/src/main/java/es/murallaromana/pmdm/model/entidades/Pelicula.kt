package es.murallaromana.pmdm.model.entidades

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Pelicula (
    var id:String?,
    @SerializedName("title")var titulo: String,
    @SerializedName("directorFullname")var autor: String?,
    @SerializedName("genre")var genero: String?,
    @SerializedName("rating")var nota: String?,
    @SerializedName("directorPhone")var telefono: String?,
    @SerializedName("imageUrl")var url: String?,
    @SerializedName("description")var resumen: String?,
        ):Serializable {
}


