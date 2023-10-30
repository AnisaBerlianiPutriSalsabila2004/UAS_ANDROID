package com.example.projek_alsa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projek_alsa.databinding.ActivityHomeScreenBinding

class homeScreen : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieList: ArrayList<film>
    private lateinit var movieAdapter: adapterFilm
    private lateinit var deskripsi: Array<String>
    private lateinit var binding:ActivityHomeScreenBinding
    companion object{
        var names = ""
        var emails = ""
        var phones = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(Home())

        names = intent.getStringExtra(loginPage.names).toString()
        emails = intent.getStringExtra(loginPage.usernames).toString()
        phones = intent.getStringExtra(loginPage.phones).toString()

        with(binding){

            bottomNav.setOnItemSelectedListener{
                when(it.itemId){
                    R.id.home_menu -> replaceFragment(Home())
                    R.id.profile_menu -> replaceFragment(Profile())

                    else->{}
                }
                true
            }
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}