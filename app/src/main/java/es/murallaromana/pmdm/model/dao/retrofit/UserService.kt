package es.murallaromana.pmdm.model.dao.retrofit


import es.murallaromana.pmdm.model.entidades.Pelicula
import es.murallaromana.pmdm.model.entidades.Token
import es.murallaromana.pmdm.model.entidades.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("movies")
    fun getPeliculas(): Call<List<Pelicula>>

    @POST("users/signup")
    fun signup(@Body user: User): Call<Unit>

    @POST("users/login")
    fun login(@Body user: User): Call<Token>
}