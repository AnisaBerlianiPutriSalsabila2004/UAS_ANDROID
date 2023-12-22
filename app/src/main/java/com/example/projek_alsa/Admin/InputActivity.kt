package com.example.projek_alsa.Admin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import com.example.projek_alsa.data.dataFilm
import com.example.projek_alsa.databinding.ActivityInputBinding
import com.example.projek_alsa.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class InputActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInputBinding
    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var storageRef : StorageReference
    private val dataFilmCollectionRef = firestore.collection("datafilm")
    private var updateId = ""
    private var imageURI : Uri? = null
    private val dataFilmListLiveData: MutableLiveData<List<dataFilm>> by lazy {
        MutableLiveData<List<dataFilm>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var command = intent.getStringExtra("COMMAND")

        with(binding){

            if(command=="UPDATE"){
                binding.btnUpdate.isVisible = true
                binding.btnAdd.isVisible = false

                updateId = intent.getStringExtra("ID").toString()
                var item_tittle = intent.getStringExtra("JUDUL")
                var item_director = intent.getStringExtra("SUTRADARA")
                var item_duration = intent.getStringExtra("DURASI")
                var item_genre = intent.getStringExtra("GENRE")
                var item_price = intent.getStringExtra("HARGA")
                var item_description = intent.getStringExtra("DESKRIPSI")

                binding.etTittleInput.setText(item_tittle.toString())
                binding.etDirectorInput.setText(item_director.toString())
                binding.etDurationInput.setText(item_duration.toString())
                binding.etGenreInput.setText(item_genre.toString())
                binding.etPriceInput.setText(item_price.toString())
                binding.etDescriptionInput.setText(item_description.toString())


            }else{
                binding.btnUpdate.isVisible = false
                binding.btnAdd.isVisible = true
            }

            btnAdd.setOnClickListener {
                if (validateInput()) {
                    val new_tittle = etTittleInput.text.toString()
                    val new_director = etDirectorInput.text.toString()
                    val new_duration = etDurationInput.text.toString()
                    val new_genre = etGenreInput.text.toString()
                    val new_price = etPriceInput.text.toString()
                    val new_description = etDescriptionInput.text.toString()

                    val newData= dataFilm(
                        tittle = new_tittle,
                        director = new_director,
                        duration = new_duration,
                        genre = new_genre,
                        price = new_price,
                        description = new_description
                    )

                    addFilm(newData)
                    val IntentToHome = Intent(this@InputActivity, adminHomeActivity::class.java)
                    Toast.makeText(this@InputActivity, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show()
                    startActivity(IntentToHome)
                }else{
                    Toast.makeText(this@InputActivity, "Kolom Tidak Boleh Kosong !!", Toast.LENGTH_SHORT).show()
                }
            }

            btnUpdate.setOnClickListener {
                if (validateInput()) {
                    val new_tittle = etTittleInput.text.toString()
                    val new_director = etDirectorInput.text.toString()
                    val new_duration = etDurationInput.text.toString()
                    val new_genre = etGenreInput.text.toString()
                    val new_price = etPriceInput.text.toString()
                    val new_description = etDescriptionInput.text.toString()
                    val dataToUpdate = dataFilm(
                        tittle = new_tittle,
                        director = new_director,
                        duration = new_duration,
                        genre = new_genre,
                        price = new_price,
                        description = new_description)

                    updateDataFilm(dataToUpdate)
                    updateId = ""
                    setEmptyField()
                    val IntentToHome = Intent(this@InputActivity, adminHomeActivity::class.java)
                    Toast.makeText(this@InputActivity, "Berhasil Mengupdate Data", Toast.LENGTH_SHORT).show()
                    startActivity(IntentToHome)
                }else{
                    Toast.makeText(this@InputActivity, "Kolom Tidak Boleh Kosong !!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()){
        imageURI = it
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

    private fun setEmptyField() {
        binding.etTittleInput.setText("")
        binding.etDirectorInput.setText("")
        binding.etDurationInput.setText("")
        binding.etGenreInput.setText("")
        binding.etPriceInput.setText("")
        binding.etDescriptionInput.setText("")
    }

    private fun validateInput(): Boolean {
        with(binding) {
            if(etTittleInput.text.toString()!="" && etDirectorInput.text.toString()!="" && etDurationInput.text.toString()!=""
                && etGenreInput.text.toString()!="" && etPriceInput.text.toString()!="" && etDescriptionInput.text.toString()!=""){
                return true
            }else{
                return false
            }
        }

    }
}