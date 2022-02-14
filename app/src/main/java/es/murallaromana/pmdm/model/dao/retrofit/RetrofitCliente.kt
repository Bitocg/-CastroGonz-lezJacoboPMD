package es.murallaromana.pmdm.model.dao.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCliente {

    private fun getRetrofit(): Retrofit{
        val retrofit = Retrofit.Builder().baseUrl("https://damapi.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    val API_RETROFIT: Api = getRetrofit().create(Api::class.java)
}