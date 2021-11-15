package es.murallaromana.pmdm.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.murallaromana.pmdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("datos",Context.MODE_PRIVATE)

        //Creo los datos que deberia haber
        val gmail = sharedPreferences.getString("gmail","usuario@gmail.com") //coges el email
        val usuario = sharedPreferences.getString("usuario","usuario") //coges el usuario
        val contraseña = sharedPreferences.getString("contraseña","contraseña")
        binding.textoEmail.setText(gmail);
        binding.textoUsuario.setText(usuario)
        binding.textoContra.setText(contraseña)


        val registro= Intent(this, RegistroMainActivity2::class.java) //para llamar a la pantalla de registro

        binding.btRegistro.setOnClickListener{  //cuando pulsemos el boton registro
            startActivity(registro) //llamamos a la pantalla de registro
        }

        val lista= Intent(this, ListaPeliculasActivity3::class.java) //para llamar a la pantalla de lista

        binding.btIniciar.setOnClickListener{
            startActivity(lista)
        }
    }

}