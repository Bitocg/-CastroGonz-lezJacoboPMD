package es.murallaromana.pmdm.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
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
            val contraseña = binding2.textContra.text.toString()

            //Compruebo que ningún campo este vacío
            if(TextUtils.equals(binding2.textContra.text.toString() , "" ) || binding2.text2Contra.text.toString()=="" ||
            binding2.textGmail.text.toString().trim()=="" || binding2.textUsuario.text.toString().trim()=="" ||
            binding2.textNombre.text.toString().trim()=="" || binding2.textApellido.text.toString().trim()=="" ) {
                Toast.makeText(this, "Hay campos vacíos",Toast.LENGTH_SHORT).show()
            }//Compuebo que el usuario no tenga espacios
            else if(binding2.textUsuario.text.toString().trim().contains(" ")){
                Toast.makeText(this, "El usuario no puede contener espacios",Toast.LENGTH_SHORT).show()
            }//Compruebo que el email no tenga espacios
            else if(binding2.textGmail.text.toString().trim().contains(" ")  ){
                Toast.makeText(this, "Email incorrecto contiene espacios",Toast.LENGTH_SHORT).show()
            }//Compuebo el formato del email
            else if(!binding2.textGmail.text.toString().trim().contains("@gmail.com") && !binding2.textGmail.text.toString().trim().contains("@hotmail.es")){
                Toast.makeText(this, "Formato del email incorrecto",Toast.LENGTH_SHORT).show()
            }//Compruebo que antes del @ haya algo en el email
            else if(binding2.textGmail.text.toString().trim().split("@")[0].length < 1 ){
                Toast.makeText(this, "Formato del email incorrecto",Toast.LENGTH_SHORT).show()
            }//Compruebo la longitud de la contraseña
            else if(contraseña.length < 6){
                Toast.makeText(this, "La contraseña tiene que tener al menos 6 caracteres",Toast.LENGTH_SHORT).show()
            }
            else if(!contieneLetras(contraseña)){
                Toast.makeText(this, "La contraseña tiene que tener al menos una letra",Toast.LENGTH_SHORT).show()
            }
            else if(!contieneNumeros(contraseña)){
                Toast.makeText(this, "La contraseña tiene que tener al menos un número",Toast.LENGTH_SHORT).show()
            }
            else if(!TextUtils.equals(binding2.textContra.text.toString() , binding2.text2Contra.text.toString())){
                Toast.makeText(this, "Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            }
            else {
                editor.putString("gmail", binding2.textGmail.text.toString())
                editor.putString("contraseña", binding2.textContra.text.toString())
                editor.putString("usuario", binding2.textUsuario.text.toString())
                editor.commit()
                startActivity(inicio)
            }
        }
    }

    fun contieneLetras(contraseña: String) : Boolean{
        val letras = arrayOf("A", "B", "C","D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
        for(letra in letras){
            if(contraseña.contains(letra,true)){
                return true
            }
        }
        return false
    }

    fun contieneNumeros(contraseña: String) : Boolean{
        val numeros = arrayOf("1", "2", "3","4", "5", "6", "7", "8", "9", "0")
        for(numero in numeros){
            if(contraseña.contains(numero)){
                return true
            }
        }
        return false
    }



}