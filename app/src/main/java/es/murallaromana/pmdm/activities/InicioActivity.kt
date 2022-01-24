package es.murallaromana.pmdm.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.murallaromana.pmdm.databinding.ActivityMainBinding

class InicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("datos",Context.MODE_PRIVATE)

        //Creo los datos que deberia haber
        val gmail = sharedPreferences.getString("gmail","usuario@gmail.com").toString() //coges el email
        val usuario = sharedPreferences.getString("usuario","usuario").toString() //coges el usuario
        val contraseña = sharedPreferences.getString("contraseña","contraseña").toString()
        binding.textoEmail.setText(gmail)
        binding.textoUsuario.setText(usuario)
        binding.textoContra.setText(contraseña)



        val registro= Intent(this, RegistroMainActivity::class.java) //para llamar a la pantalla de registro
            binding.btRegistro.setOnClickListener {  //cuando pulsemos el boton registro
                startActivity(registro) //llamamos a la pantalla de registro
            }


        val lista= Intent(this, ListaPeliculasActivity::class.java) //para llamar a la pantalla de lista

        binding.btIniciar.setOnClickListener{
            if(gmail!=binding.textoEmail.text.toString() || usuario!=binding.textoUsuario.text.toString() || contraseña!=binding.textoContra.text.toString()) {
                Toast.makeText(this , "Datos incorrectos", Toast.LENGTH_SHORT).show()
            }else if(gmail=="usuario@gmail.com" || usuario =="usuario" || contraseña=="contraseña"){
                Toast.makeText(this , "Tienes que registrarte", Toast.LENGTH_SHORT).show()
            }
            else{
                startActivity(lista)
            }
        }


    }
}