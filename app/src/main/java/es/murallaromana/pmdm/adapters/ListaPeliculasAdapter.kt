package es.murallaromana.pmdm.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.murallaromana.pmdm.R
import es.murallaromana.pmdm.activities.detalleActivity
import es.murallaromana.pmdm.model.entidades.Pelicula

class ListaPeliculasAdapter(val peliculas : List<Pelicula>, val activity: Activity) :
    RecyclerView.Adapter<ListaPeliculasAdapter.PeliculaHolder>(){

    // Este método mantiene referencia a los componentes visuales (en este caso al nombre de la peli)
    class PeliculaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtGenero = itemView.findViewById<TextView>(R.id.txtGenero)
        val itemLista = itemView.findViewById<ConstraintLayout>(R.id.itemLista)
        val txtTitulo = itemView.findViewById<TextView>(R.id.txtTitulo)
        val txtNota = itemView.findViewById<TextView>(R.id.txtNota)
        val txtAutor = itemView.findViewById<TextView>(R.id.txtAutor)
        val imagen = itemView.findViewById<ImageView>(R.id.image)

    }

    // Este método se ocupa de INFLAR la vista (el item_country.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):PeliculaHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_peliculas, parent, false)
        return PeliculaHolder(inflater)
    }

    // Este método devuelve el número de elementos de la lista
    override fun onBindViewHolder(holder: PeliculaHolder, position: Int) {
        val pelicula = peliculas.get(position)

        holder.txtGenero.text= "Género: "+pelicula.genero
        holder.txtTitulo.text= "Título: "+pelicula.titulo
        holder.txtNota.text= "Nota: "+pelicula.nota
        holder.txtAutor.text= "Autor: "+pelicula.autor

        //Libreria picasso
        Picasso.get().load(pelicula.url).into(holder.imagen)




        holder.itemLista.setOnClickListener() {
            val intent = Intent(activity,detalleActivity::class.java)
            intent.putExtra("pelicula",pelicula)
            activity.startActivity(intent)
        }

    }

    // Este método se llama tantas veces como elementos hay en la lista
    override fun getItemCount(): Int {
        return peliculas.size;
    }


}