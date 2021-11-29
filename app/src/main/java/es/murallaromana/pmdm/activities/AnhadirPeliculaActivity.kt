package es.murallaromana.pmdm.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import es.murallaromana.pmdm.App
import es.murallaromana.pmdm.R
import es.murallaromana.pmdm.databinding.ActivityAnhadirPeliculaBinding
import es.murallaromana.pmdm.model.dao.PeliculaDao
import es.murallaromana.pmdm.model.dao.PeliculasDaoMocklmpl
import es.murallaromana.pmdm.model.entidades.Pelicula

class AnhadirPeliculaActivity: AppCompatActivity() {
    private  lateinit var binding: ActivityAnhadirPeliculaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAnhadirPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pelicula = intent.extras?.get("pelicula") as Pelicula?


        if(pelicula!=null) {
            setTitle("Editar o borrar película")
            binding.txtTituloA.setText(pelicula.titulo)
            binding.txtAutorA.setText(pelicula.autor)
            binding.txtGeneroA.setText(pelicula.genero)
            binding.txtNotaA.setText(pelicula.nota)
            binding.txtTelefonoA.setText(pelicula.telefono)
            binding.txtUrlA.setText(pelicula.url)
            binding.txtResumenA.setText(pelicula.resumen)

        }else{
            setTitle("Añadir película")

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_delete){ //si pulsas en la papelera

            val pelicula = intent.extras?.get("pelicula") as Pelicula?
            val posicion = intent.extras?.get("posicion") as Int?

            if(pelicula!=null){ //Si hay una pelicula que la borre
                if (posicion != null) {
                    val mensaje = AlertDialog.Builder(this)
                    mensaje.setTitle("Borrar pelicula")
                    //mensaje.setMessage("Seguro que quieres borrarla:")
                    mensaje.setPositiveButton("Si") { dialogInterface, i ->
                        App.peliculas.removeAt(posicion)
                        Toast.makeText(this , "Película borrada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    mensaje.setNegativeButton("No") { dialogInterface, i ->
                    }
                    mensaje.show()
                }
            }
            else{
                //Que vacíe los txt
                binding.txtAutorA.setText("")
                binding.txtTituloA.setText("")
                binding.txtGeneroA.setText("")
                binding.txtNotaA.setText("")
                binding.txtTelefonoA.setText("")
                binding.txtUrlA.setText("")
                binding.txtResumenA.setText("")
                Toast.makeText(this , "Campos borrados", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        else if(item.itemId == R.id.action_save_or_update){ //si pulsas en el check añades
            val pelicula = intent.extras?.get("pelicula") as Pelicula?
            if(pelicula!=null){ //Si ya hay una película la borra y crea una nueva
                if(TextUtils.equals(binding.txtAutorA.text.toString(),"") || TextUtils.equals(binding.txtTituloA.text.toString(),"")
                    || TextUtils.equals(binding.txtGeneroA.text.toString(),"") || TextUtils.equals(binding.txtNotaA.text.toString(),"")
                    || TextUtils.equals(binding.txtTelefonoA.text.toString(),"") || TextUtils.equals(binding.txtUrlA.text.toString(),"")
                    || TextUtils.equals(binding.txtResumenA.text.toString(),""))
                {
                    Toast.makeText(this, "Hay campos vacíos",Toast.LENGTH_SHORT).show()
                }else{
                    val posicion = intent.extras?.get("posicion") as Int?
                    if (posicion != null) {
                        App.peliculas.removeAt(posicion)
                    }
                    App.peliculas.add(
                            Pelicula(
                                binding.txtTituloA.text.toString(),
                                binding.txtAutorA.text.toString(),
                                binding.txtGeneroA.text.toString(),
                                binding.txtNotaA.text.toString(),
                                binding.txtTelefonoA.text.toString(),
                                binding.txtUrlA.text.toString(),
                                binding.txtResumenA.text.toString(),
                            )
                    )
                    Toast.makeText(this , "Película editada", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            else{//Sino hay pelicula creamos una nueva película
                if(TextUtils.equals(binding.txtAutorA.text.toString(),"") || TextUtils.equals(binding.txtTituloA.text.toString(),"")
                    || TextUtils.equals(binding.txtGeneroA.text.toString(),"") || TextUtils.equals(binding.txtNotaA.text.toString(),"")
                    || TextUtils.equals(binding.txtTelefonoA.text.toString(),"") || TextUtils.equals(binding.txtUrlA.text.toString(),"")
                    || TextUtils.equals(binding.txtResumenA.text.toString(),""))
                {
                    Toast.makeText(this, "Hay campos vacíos",Toast.LENGTH_SHORT).show()
                }else{
                    App.peliculas.add(
                        Pelicula(
                            binding.txtTituloA.text.toString(),
                            binding.txtAutorA.text.toString(),
                            binding.txtGeneroA.text.toString(),
                            binding.txtNotaA.text.toString(),
                            binding.txtTelefonoA.text.toString(),
                            binding.txtUrlA.text.toString(),
                            binding.txtResumenA.text.toString(),
                        )
                    )
                    Toast.makeText(this , "Nueva película añadida", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            return true
        }
        else{
            return super.onOptionsItemSelected(item)
        }
    }
}
