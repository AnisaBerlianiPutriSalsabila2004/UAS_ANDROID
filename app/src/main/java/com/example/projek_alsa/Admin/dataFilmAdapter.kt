package com.example.projek_alsa.Admin

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.projek_alsa.R
import com.example.projek_alsa.data.dataFilm
import com.example.projek_alsa.swipeToDeleteCallback
import com.google.firebase.firestore.FirebaseFirestore

class dataFilmAdapter(val dataFilm: List<dataFilm>?): RecyclerView.Adapter<dataFilmAdapter.MyViewHolder>() {
    private val firestore = FirebaseFirestore.getInstance()
    private val dataFilmCollectionRef = firestore.collection("datafilm")
    private lateinit var binding: adminHomeActivity
    private var updateId = ""
    private val dataFilmListLiveData: MutableLiveData<List<dataFilm>> by lazy {
        MutableLiveData<List<dataFilm>>()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tittle = view.findViewById<TextView>(R.id.txt_tittle_rvnotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvnotes, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dataFilm != null) {
            return dataFilm.size
        }
        return 0
    }

    fun getDataAtPosition(position: Int): dataFilm? {
        return dataFilm?.get(position)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tittle.text = "Movie : ${dataFilm?.get(position)?.tittle}"

        holder.itemView.setOnClickListener {
//            val intentToDetail = Intent(holder.itemView.context, detailFilmActivity::class.java)
//            intentToDetail.putExtra("JUDUL", dataFilm?.get(position)?.tittle)
//            intentToDetail.putExtra("SUTRADARA", dataFilm?.get(position)?.director)
//            intentToDetail.putExtra("DURASI", dataFilm?.get(position)?.duration)
//            intentToDetail.putExtra("DESKRIPSI", dataFilm?.get(position)?.description)
//            intentToDetail.putExtra("HARGA", dataFilm?.get(position)?.price)
//            intentToDetail.putExtra("GENRE", dataFilm?.get(position)?.genre)
//            holder.itemView.context.startActivity(intentToDetail)
            val intentToUpdate = Intent(holder.itemView.context, InputActivity::class.java)
            intentToUpdate.putExtra("ID", dataFilm?.get(position)?.id)
            intentToUpdate.putExtra("JUDUL", dataFilm?.get(position)?.tittle)
            intentToUpdate.putExtra("SUTRADARA", dataFilm?.get(position)?.director)
            intentToUpdate.putExtra("DURASI", dataFilm?.get(position)?.duration)
            intentToUpdate.putExtra("DESKRIPSI", dataFilm?.get(position)?.description)
            intentToUpdate.putExtra("HARGA", dataFilm?.get(position)?.price)
            intentToUpdate.putExtra("GENRE", dataFilm?.get(position)?.genre)
            intentToUpdate.putExtra("COMMAND", "UPDATE")
            holder.itemView.context.startActivity(intentToUpdate)
        }

        val swipeToDeleteCallback = object : swipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
            }
        }
    }
}