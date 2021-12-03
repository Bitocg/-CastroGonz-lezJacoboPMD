package es.murallaromana.pmdm.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.murallaromana.pmdm.R
import es.murallaromana.pmdm.activities.AnhadirPeliculaActivity
import es.murallaromana.pmdm.activities.DetalleActivity
import es.murallaromana.pmdm.model.entidades.Pelicula

class ListaPeliculasAdapter(val peliculas : MutableList<Pelicula>, val activity: Activity ,val context: Context) :

    RecyclerView.Adapter<ListaPeliculasAdapter.PeliculaHolder>() {


    // Este método mantiene referencia a los componentes visuales (en este caso al nombre de la peli)
    class PeliculaHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtGenero = itemView.findViewById<TextView>(R.id.txtGenero)
        val itemLista = itemView.findViewById<CardView>(R.id.itemLista)
        val txtTitulo = itemView.findViewById<TextView>(R.id.txtTitulo)
        val txtNota = itemView.findViewById<TextView>(R.id.txtNota)
        val txtAutor = itemView.findViewById<TextView>(R.id.txtAutor)
        val imagen = itemView.findViewById<ImageView>(R.id.image)

    }

    /*class PeliculaViewHolder(val itemBinding : ItemPeliculasBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun enlazarDatos(pelicula: Pelicula) {
            itemBinding.txtAutor.setText(pelicula.autor)

            val inten = Intent(itemBinding.root.context, detalleActivity::class.java)

        }
    }*/


    // Este método se ocupa de INFLAR la vista (el item_country.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_peliculas, parent, false)
        return PeliculaHolder(inflater)

        /* si lo quiero hacer de la otra forma
        val layoutInflater = LayoutInflater.from(parent.context)
        return PeliculaViewHolder(ItemPeliculasBinding.inflate(layoutInflater,parent,false))
        */
    }

    // Este método devuelve el número de elementos de la lista
    override fun onBindViewHolder(holder: PeliculaHolder, position: Int) {
        val pelicula = peliculas.get(position)

        // holder.enlazarDatos(pelicula) si lo hago de la otra forma con PeliculaViewHolder
        holder.txtGenero.text = "Género: " + pelicula.genero
        holder.txtTitulo.text = "Título: " + pelicula.titulo
        holder.txtNota.text = pelicula.nota
        holder.txtAutor.text = "Autor: " + pelicula.autor

        //Libreria picasso
        Picasso.get().load(pelicula.url).into(holder.imagen)

        holder.itemLista.setOnClickListener() {
            val mensaje = AlertDialog.Builder(context)
            mensaje.setTitle("Pelicula")
            mensaje.setMessage("Que quieres hacer:")
            mensaje.setPositiveButton("Ver los detalles de la película") { dialogInterface, i ->
                val intent = Intent(context, DetalleActivity::class.java)
                intent.putExtra("pelicula", pelicula)


                activity.startActivity(intent)
            }
            mensaje.setNegativeButton("Editar o borrar película") { dialogInterface, i ->
                val editarPeli = Intent(context,AnhadirPeliculaActivity::class.java)
                editarPeli.putExtra("pelicula", pelicula)
                editarPeli.putExtra("posicion", position)

                activity.startActivity(editarPeli)
            }
            mensaje.show()

        }

    }

    // Este método se llama tantas veces como elementos hay en la lista
    override fun getItemCount(): Int {
        return peliculas.size
    }
}



