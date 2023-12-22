package com.example.projek_alsa.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projek_alsa.R
import com.example.projek_alsa.databinding.ActivityDetailFilmBinding

class detailFilmActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var item_tittle = intent.getStringExtra("JUDUL")
        var item_director = intent.getStringExtra("SUTRADARA")
        var item_duration = intent.getStringExtra("DURASI")
        var item_genre = intent.getStringExtra("GENRE")
        var item_price = intent.getStringExtra("HARGA")
        var item_description = intent.getStringExtra("DESKRIPSI")

        with(binding){
            txtTittleDetailMovieAdmin.text = "Movie :  ${item_tittle}"
            txtDirectorDetailMovieAdmin.text = "Movie :  ${item_director}"
            txtDurationDetailMovieAdmin.text = "Movie :  ${item_duration}"
            txtGenreDetailMovieAdmin.text = "Movie :  ${item_genre}"
            txtPriceDetailMovieAdmin.text = "Movie :  ${item_price}"
            txtDescriptionDetailMovieAdmin.text = "Movie :  ${item_description}"


            btnKembali.setOnClickListener{
                val IntentToHome = Intent(this@detailFilmActivity, adminHomeActivity::class.java)
                startActivity(IntentToHome)
            }
        }
    }
}