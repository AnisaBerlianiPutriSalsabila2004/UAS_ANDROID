package com.example.projek_alsa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projek_alsa.databinding.ActivitySummaryBinding

class summary : AppCompatActivity() {
    private lateinit var binding : ActivitySummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var film = intent.getParcelableExtra<film>("film")
        val bioskop = intent.getStringExtra(payment.cinema)
        val seat = intent.getStringExtra(payment.ticketType)
        val tanggal = intent.getStringExtra(payment.tanggal)
        val harga = intent.getStringExtra(payment.price)
        val payment = intent.getStringExtra(payment.paymentMethod)
        val bank = intent.getStringExtra(com.example.projek_alsa.payment.bank)

        with(binding){


            if (film != null) {
                judulTxt.text = film.name
                imgs.setImageResource(film.bg_img)
            }

            tanggalTxt.text = tanggal
            cinemaTxt.text = bioskop
            SEAT.text = seat
            PRICE.text = harga
            if(payment != "Cash") {
                PAYMENT.text = "$payment, ($bank)"
            }else{
                PAYMENT.text = "$payment"
            }
        }
    }
}