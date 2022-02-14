package es.murallaromana.pmdm.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import es.murallaromana.pmdm.model.dao.retrofit.RetrofitCliente
import es.murallaromana.pmdm.databinding.ActivityMainBinding
import es.murallaromana.pmdm.model.dao.SharedPreference
import es.murallaromana.pmdm.model.entidades.Pelicula
import es.murallaromana.pmdm.model.entidades.Token
import es.murallaromana.pmdm.model.entidades.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //a
        sharedPreference = SharedPreference(applicationContext)
        val context = this
        val sharedPreferences = getSharedPreferences("datos",Context.MODE_PRIVATE)

        //Creo los datos que debería haber
        val gmail = sharedPreferences.getString("gmail","c3@gmail.com").toString() //coges el email
        //val usuario = sharedPreferences.getString("usuario","usuario").toString() //coges el usuario
        val contraseña = sharedPreferences.getString("contraseña","1").toString()
        binding.textoEmail.setText(gmail)
        //binding.textoUsuario.setText(usuario)
        binding.textoContra.setText(contraseña)


        val llamarApi: Call<List<Pelicula>> = RetrofitCliente.API_RETROFIT.getPeliculas()
        llamarApi.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(call: Call<List<Pelicula>>,response: Response<List<Pelicula>>) {
                Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("Prueba", t.message.toString())
            }
        })

        val registro= Intent(this, RegistroMainActivity::class.java) //para llamar a la pantalla de registro
            binding.btRegistro.setOnClickListener {  //cuando pulsemos el boton registro
                startActivity(registro) //llamamos a la pantalla de registro
            }


        val lista= Intent(this, ListaPeliculasActivity::class.java) //para llamar a la pantalla de lista

        binding.btIniciar.setOnClickListener {
            /* if(gmail!=binding.textoEmail.text.toString() || usuario!=binding.textoUsuario.text.toString() || contraseña!=binding.textoContra.text.toString()) {
                Toast.makeText(this , "Datos incorrectos", Toast.LENGTH_SHORT).show()
            }else if(gmail=="usuario@gmail.com" || usuario =="usuario" || contraseña=="contraseña"){
                Toast.makeText(this , "Tienes que registrarte", Toast.LENGTH_SHORT).show()
            }
            else{
                startActivity(lista)
            }*/
            val correo = binding.textoEmail.text.toString()
            val contraseña = binding.textoContra.text.toString()
            val call: Call<Token> = RetrofitCliente.API_RETROFIT.login(User(correo, contraseña))
            call.enqueue(object : Callback<Token> {
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())
                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    Log.d("respuesta: onResponse", response.toString())
                    if (response.code() > 299 || response.code() < 200) {
                        Toast.makeText(this@InicioActivity,"Inicio de sesión fallido",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@InicioActivity,"Accediendo a lista",Toast.LENGTH_SHORT).show()
                        val token = response.body()?.token.toString()
                        sharedPreference.guardar(token)
                        startActivity(lista)
                    }
                }
            })
        }
    }
}