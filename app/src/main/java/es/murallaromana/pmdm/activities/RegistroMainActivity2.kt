package es.murallaromana.pmdm.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import es.murallaromana.pmdm.databinding.ActivityMainBinding
import es.murallaromana.pmdm.databinding.ActivityRegistroMain2Binding

class RegistroMainActivity2 : AppCompatActivity() {


    private lateinit var binding2: ActivityRegistroMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding2 = ActivityRegistroMain2Binding.inflate(layoutInflater)
        setContentView(binding2.root)

        //Botón que si le das va para atrás
        binding2.btAtras.setOnClickListener{
            onBackPressed()
        }

        setTitle("Registro de películas")

        val inicio= Intent(this, MainActivity::class.java) //para llamar al inicio

        //botón registrarse las guarda en las sharedPreference de android
        binding2.btregistrarse.setOnClickListener {
            val sharedPref = getSharedPreferences("datos",Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putString("gmail", binding2.textGmail.text.toString())
            editor.putString("contraseña", binding2.textContra.text.toString())
            editor.putString("usuario", binding2.textUsuario.text.toString())
            editor.commit()
            startActivity(inicio)
        }
    }
}