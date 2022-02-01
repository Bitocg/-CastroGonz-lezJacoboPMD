package es.murallaromana.pmdm.model.entidades

import com.google.gson.annotations.SerializedName

class User (
    var email: String,
    @SerializedName("password") var contraseña: String
)


