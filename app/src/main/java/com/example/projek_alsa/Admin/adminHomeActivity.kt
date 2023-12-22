package com.example.projek_alsa.Admin

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projek_alsa.databinding.ActivityMainBinding
import com.example.projek_alsa.Admin.dataFilmAdapter
import com.example.projek_alsa.data.dataFilm
import com.example.projek_alsa.databinding.ActivityAdminHomeBinding
import com.example.projek_alsa.swipeToDeleteCallback
import com.google.firebase.firestore.FirebaseFirestore

class adminHomeActivity : AppCompatActivity() {
    private val firestore = FirebaseFirestore.getInstance()
    private val dataFilmCollectionRef = firestore.collection("datafilm")
    private lateinit var binding: ActivityAdminHomeBinding
    private var updateId = ""
    private val dataFilmListLiveData: MutableLiveData<List<dataFilm>> by lazy {
        MutableLiveData<List<dataFilm>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeDataFilmChanges()
        getAlldataFilm()

        with(binding) {
            btnAdd.setOnClickListener{
                val intentToInput = Intent(this@adminHomeActivity, InputActivity::class.java)
                intentToInput.putExtra("COMMAND", "ADD")
                startActivity(intentToInput)
            }
        }
    }

    private fun getAlldataFilm() {
        observedataFilms()
    }

    private fun observedataFilms() {
        dataFilmListLiveData.observe(this) { datafilm ->
            if (datafilm.isNotEmpty()) { // Periksa apakah daftar catatan tidak kosong
                Log.d(TAG, "observedataFilms: ${datafilm.size}")
                binding.rvRvnotes.isVisible = true
                binding.textEmpty.isVisible = false
                val recyclerAdapter = dataFilmAdapter(datafilm)

                val swipeToDeleteCallback = object : swipeToDeleteCallback() {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        val deletedItem = recyclerAdapter.getDataAtPosition(position)
                        if (deletedItem != null) {
                            deleteDataFilm(deletedItem)
                        }
                        if (deletedItem != null) {
                            Toast.makeText(
                                this@adminHomeActivity,
                                "${deletedItem.tittle} deleted",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            Toast.makeText(
                                this@adminHomeActivity,
                                "data deleted",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                binding.rvRvnotes.apply {
                    layoutManager = LinearLayoutManager(this@adminHomeActivity)
                    setHasFixedSize(true)
                    adapter = recyclerAdapter
                }

                val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
                itemTouchHelper.attachToRecyclerView(binding.rvRvnotes)

            }else{
                Toast.makeText(this@adminHomeActivity, "KOSONG", Toast.LENGTH_SHORT).show()
                binding.rvRvnotes.isVisible = false
                binding.textEmpty.isVisible = true
            }
        }
    }

    private fun observeDataFilmChanges() {
        dataFilmCollectionRef.addSnapshotListener { snapshots, error ->
            if (error != null) {
                Log.d("MainActivity", "Data not found: ", error)
                return@addSnapshotListener
            }
            val datafilm = snapshots?.toObjects(dataFilm::class.java)
            if (datafilm != null) {
                dataFilmListLiveData.postValue(datafilm)
            }
        }
    }
    private fun addFilm(datafilm: dataFilm) {
        dataFilmCollectionRef.add(datafilm)
            .addOnSuccessListener { documentReference ->
                val createdDataFilmId = documentReference.id
                datafilm.id = createdDataFilmId
                documentReference.set(datafilm)
                    .addOnFailureListener {
                        Log.d("MainActivity", "Error updating data ID: ", it)
                    }
            }
            .addOnFailureListener {
                Log.d("MainActivity", "Error adding data: ", it)
            }
    }
    private fun updateDataFilm(datafilm: dataFilm) {
        datafilm.id = updateId
        dataFilmCollectionRef.document(updateId).set(datafilm)
            .addOnFailureListener {
                Log.d("MainActivity", "Error updating data: ", it)
            }
    }
    private fun deleteDataFilm(datafilm: dataFilm) {
        if (datafilm.id.isEmpty()) {
            Log.d("MainActivity", "Error deleting: data ID is empty!")
            return
        }
        dataFilmCollectionRef.document(datafilm.id).delete()
            .addOnFailureListener {
                Log.d("MainActivity", "Error deleting data: ", it)
            }
    }
}