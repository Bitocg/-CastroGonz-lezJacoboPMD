package es.murallaromana.pmdm.model.dao.retrofit


import es.murallaromana.pmdm.model.entidades.Pelicula
import es.murallaromana.pmdm.model.entidades.Token
import es.murallaromana.pmdm.model.entidades.User
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("movies")
    fun getPeliculas(): Call<List<Pelicula>>

    @GET("movies")
    fun getPeliculas2(@Header("Authorization") token:String): Call<List<Pelicula>>

    @POST("users/signup")
    fun signup(@Body user: User): Call<Unit>

    @POST("users/login")
    fun login(@Body user: User): Call<Token>

    @POST("movies")
    fun crear(@Header("Authorization") token: String,
              @Body pelicula: Pelicula): Call<Unit>

    @PUT("movies")
    fun editar(@Header("Authorization") token: String,
               @Body pelicula: Pelicula): Call<Pelicula>

    @DELETE("movies/{id}")
    fun delete(@Header("Authorization") token: String,
               @Path("id") id:String?):Call<Pelicula>

    @GET("movies/{id}?")
    fun getId(@Header("Authorization") token: String,
              @Path("id") id:String?):Call<Pelicula>
}