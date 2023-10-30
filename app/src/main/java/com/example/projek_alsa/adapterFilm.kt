package com.example.projek_alsa

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView


class adapterFilm (private val movieList:ArrayList<film>)
    :RecyclerView.Adapter<adapterFilm.movieViewHolder>(){

    var onItemClick : ((film) -> Unit)? = null

    class movieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rate : ImageView = itemView.findViewById(R.id.rate)
        val imageView : ImageView = itemView.findViewById(R.id.gambar)
        val tittle : TextView = itemView.findViewById(R.id.judul)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return movieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: movieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.imageView.setImageResource(movie.bg_img)
        holder.tittle.text = movie.name
        holder.rate.setImageResource(movie.rate)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(movie)
        }
    }
}