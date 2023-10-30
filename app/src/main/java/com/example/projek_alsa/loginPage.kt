package com.example.projek_alsa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projek_alsa.databinding.ActivityLoginPageBinding

class loginPage : AppCompatActivity() {
    private lateinit var binding:ActivityLoginPageBinding

    companion object{
        var usernames = "usernames"
        val names = "names"
        var passwords = "passwords"
        var phones = "phone"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var username = intent.getStringExtra(registerPage.usernames)
        var name = intent.getStringExtra(registerPage.names)
        var password = intent.getStringExtra(registerPage.passwords)
        var phone = intent.getStringExtra(registerPage.phones)

        with(binding){
            loginBtn.setOnClickListener{
                if(emailTxt.text.toString() == username && passwordTxt.text.toString() == password){
                    val intentHome = Intent(this@loginPage, homeScreen::class.java)
                    intentHome.putExtra(usernames, username)
                    intentHome.putExtra(names, name)
                    intentHome.putExtra(passwords, password)
                    intentHome.putExtra(phones, phone)
                    startActivity(intentHome)
                }else{
                    Toast.makeText(this@loginPage, "Password / Username salah", Toast.LENGTH_SHORT).show()
                }
            }

            registerLink.setOnClickListener{
                val intentRegis = Intent(this@loginPage, registerPage::class.java)
                startActivity(intentRegis)
            }
        }
    }
}