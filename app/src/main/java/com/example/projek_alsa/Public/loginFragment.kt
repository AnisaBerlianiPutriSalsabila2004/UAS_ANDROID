package com.example.projek_alsa.Public

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.pojek_alsa.data.Users
import com.example.projek_alsa.Admin.adminHomeActivity
import com.example.projek_alsa.R
import com.example.projek_alsa.sharedPreferences.prefData
import com.example.projek_alsa.sharedPreferences.sharedPreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [loginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class loginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btLog: Button
    private lateinit var sharedPref : sharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentLogin = inflater.inflate(R.layout.fragment_login, container, false)
        sharedPref = sharedPreference(requireActivity())
        etEmail = fragmentLogin.findViewById(R.id.et_email_login)
        etPassword = fragmentLogin.findViewById(R.id.et_password_login)
        btLog = fragmentLogin.findViewById(R.id.login_btn)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        btLog.setOnClickListener {
            val signUpEmail = etEmail.text.toString()
            val signUpPassword = etPassword.text.toString()
            if( signUpPassword.isNotEmpty() && signUpEmail.isNotEmpty()) {
                login(signUpEmail, signUpPassword)
            }else{
                Toast.makeText(activity, "All field cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        // Inflate the layout for this fragment
        return fragmentLogin
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment loginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            loginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun login(email:String, password:String ){
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    Toast.makeText(activity, "berhasil", Toast.LENGTH_SHORT).show()
                    for(userSnapshots in snapshot.children){
                        val userData = userSnapshots.getValue(Users::class.java)
                        if(userData!=null && userData.password == password){
                            Toast.makeText(activity, "Login succesfull", Toast.LENGTH_SHORT).show()
                            if(userData.Roles == "PUBLIC"){
                                sharedPref.put( prefData.PREF_ID, userData.id)
                                sharedPref.put( prefData.PREF_USERNAME, userData.Username)
                                sharedPref.put( prefData.PREF_PHONE, userData.Phone)
                                sharedPref.put( prefData.PREF_EMAIL, userData.email)
                                sharedPref.put(prefData.PREF_IS_LOGIN, true)
                                startActivity(Intent(activity, publicHomeActivity::class.java))
                                requireActivity().finish()
                                return
                            }else{
                                startActivity(Intent(activity, adminHomeActivity::class.java))
                                requireActivity().finish()
                                return
                            }
                        }
                        Toast.makeText(activity, "Failed, username or password invalid", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Databse error : ${error}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}