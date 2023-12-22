//package com.example.projek_alsa
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import com.example.projek_alsa.databinding.ActivityRegisterPageBinding
//
//class registerPage : AppCompatActivity() {
//    private lateinit var binding: ActivityRegisterPageBinding
//
//    companion object{
//        var usernames = "user"
//        val names = "nama"
//        var passwords = "password"
//        var phones = "phone"
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        with(binding){
//            regisBtn.setOnClickListener{
//                if(emailTxt.text.toString()!="" && passwordTxt.text.toString()!="" && namaTxt.text.toString()!=""){
//                    val intentLogin = Intent(this@registerPage, loginPage::class.java)
//                    intentLogin.putExtra(usernames, emailTxt.text.toString())
//                    intentLogin.putExtra(passwords, passwordTxt.text.toString())
//                    intentLogin.putExtra(names, namaTxt.text.toString())
//                    intentLogin.putExtra(phones, phoneTxt.text.toString())
//                    startActivity(intentLogin)
//                }else{
//                    Toast.makeText(this@registerPage, "Kolom tidak boleh kosong", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//}