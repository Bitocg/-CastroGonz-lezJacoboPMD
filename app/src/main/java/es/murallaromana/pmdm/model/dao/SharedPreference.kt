package es.murallaromana.pmdm.model.dao

import android.content.Context
import es.murallaromana.pmdm.model.entidades.Token

class SharedPreference(
    val context: Context
) {
    val nombreArchivo = "base de datos"
    val preferences = context.getSharedPreferences(nombreArchivo,0)

    fun guardar(token: String){
        preferences.edit().putString("token",token).commit()
    }

    fun recogerToken(): String?{
        return preferences.getString("token","")
    }
}