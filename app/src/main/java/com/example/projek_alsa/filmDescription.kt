package com.example.projek_alsa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projek_alsa.databinding.ActivityFilmDescriptionBinding

class filmDescription : AppCompatActivity() {
    companion object {
        var judul_film = ""
        var gambar = ""
    }

    private lateinit var binding : ActivityFilmDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilmDescriptionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var film = intent.getParcelableExtra<film>("film")
        if (film != null){
            binding.bgGambar.setImageResource(film.ico_img)
            binding.imgIcon.setImageResource(film.bg_img)
            binding.genreTxt.text = "Genre : ${film.genre}"
            binding.judulTxt.text = film.name
            binding.rateImg.setImageResource(film.rate)
            binding.sutradaraTxt.text = "Directed by : " + film.sutradara
            binding.ratingTxt.text = film.rate_txt
            binding.sinopsisTxt.text = film.deskripsiFilm
        }

        with(binding){
            buyBtn.setOnClickListener {
                val intentPayment = Intent(this@filmDescription, payment::class.java)
                if (film != null) {
                    intentPayment.putExtra("film", film)
                    Toast.makeText(this@filmDescription, "${film.name}", Toast.LENGTH_SHORT).show()
                    startActivity(intentPayment)
                }
            }
        }
    }
}