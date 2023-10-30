package com.example.projek_alsa


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import com.example.projek_alsa.databinding.ActivityPaymentBinding

private val BIOSKOP = arrayOf(
    "XXI JCM",
    "XXI Ambarukmo Plaza",
    "CGV Pakuwon",
    "CGV Transmart Maguwo"
)

private val JENIS = arrayOf(
    "Reguler Seat",
    "4D",
    "VIP"
)

private val PAYMENT = arrayOf(
    "Bank Transfer",
    "Cash",
)

private val BANK = arrayOf(
    "BNI",
    "BRI",
    "Mandiri",
    "BCA",
    "Cimbniaga"
)

class payment : AppCompatActivity() {
    companion object {
        var film_name =""
        var image_url =""
        var cinema =""
        var ticketType =""
        var price =""
        var tanggal =""
        var paymentMethod =""
        var bank =""
        var accountNumber =""
    }

    private lateinit var binding : ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapterBioskop = ArrayAdapter(this@payment, android.R.layout.simple_spinner_item, BIOSKOP);
        adapterBioskop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinerBioskop.adapter = adapterBioskop

        binding.spinerBioskop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent : AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItemPos = parent.getItemAtPosition(position).toString()
                cinema = selectedItemPos
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val adapterJenisTiket = ArrayAdapter(this@payment, android.R.layout.simple_spinner_item, JENIS);
        adapterJenisTiket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinerJenisseat.adapter = adapterJenisTiket

        binding.spinerJenisseat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent : AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItemPos = parent.getItemAtPosition(position).toString()
                ticketType = selectedItemPos
                if(ticketType == "Reguler Seat"){
                    price = "35000"
                }else if(ticketType == "4D"){
                    price = "70000"
                }else{
                    price = "100000"
                }
                binding.hargaTxt.text = price
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val adapterPayment = ArrayAdapter(this@payment, android.R.layout.simple_spinner_item, PAYMENT);
        adapterPayment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinerPaymethod.adapter = adapterPayment

        binding.spinerPaymethod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent : AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItemPos = parent.getItemAtPosition(position).toString()
                paymentMethod = selectedItemPos
                if(paymentMethod=="Cash"){
                    binding.kolomBank.isVisible = false;
                }else{
                    binding.kolomBank.isVisible = true;
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val adapterJenisBank = ArrayAdapter(this@payment, android.R.layout.simple_spinner_item, BANK);
        adapterJenisBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinerBank.adapter = adapterJenisBank

        binding.spinerBank.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent : AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItemPos = parent.getItemAtPosition(position).toString()
                bank = selectedItemPos
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        with(binding){

            var film = intent.getParcelableExtra<film>("film")

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ) { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                tanggal = selectedDate
            }


            buttonSubmit.setOnClickListener{
                val intentEnd = Intent(this@payment, summary::class.java)
                intentEnd.putExtra("film", film)
                intentEnd.putExtra(cinema, cinema)
                intentEnd.putExtra(ticketType, ticketType)
                intentEnd.putExtra(tanggal, tanggal)
                intentEnd.putExtra(price, price)
                intentEnd.putExtra(paymentMethod, paymentMethod)
                if(paymentMethod != "Cash"){
                    intentEnd.putExtra(bank, bank)
                    intentEnd.putExtra(accountNumber, rekeningInp.text.toString())
                }
                startActivity(intentEnd)
            }
        }
    }
}

