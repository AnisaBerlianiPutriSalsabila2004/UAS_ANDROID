package com.example.projek_alsa.Public

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.pojek_alsa.data.Users
import com.example.projek_alsa.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [registerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class registerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var etUsername: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btCreate: Button
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private val firestore = FirebaseFirestore.getInstance()
    private val userCollectionRef = firestore.collection("Users")
    private lateinit var database: FirebaseFirestore
    private var updateId = ""
    private val userLivelistData: MutableLiveData<List<Users>> by lazy {
        MutableLiveData<List<Users>>()
    }

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

        val fragmentLogin = inflater.inflate(R.layout.fragment_register, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")
        etUsername = fragmentLogin.findViewById(R.id.et_name_regis)
        etPhone = fragmentLogin.findViewById(R.id.et_phone_regis)
        etEmail = fragmentLogin.findViewById(R.id.et_email_regis)
        etPassword = fragmentLogin.findViewById(R.id.et_password_regis)
        btCreate = fragmentLogin.findViewById(R.id.bt_create_regis)

        btCreate.setOnClickListener {
            val signUpUsername = etUsername.text.toString()
            val signUpPhone = etPhone.text.toString()
            val signUpEmail = etEmail.text.toString()
            val signUpPassword = etPassword.text.toString()

            if(signUpUsername.isNotEmpty() && signUpPassword.isNotEmpty()&& signUpPhone.isNotEmpty() && signUpEmail.isNotEmpty()) {
                signUp(signUpUsername, signUpPhone, "PUBLIC", signUpEmail, signUpPassword)
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
         * @return A new instance of fragment registerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            registerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun signUp(username:String, phone:String, role:String, email:String, password:String){
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                Toast.makeText(activity, "berhasil", Toast.LENGTH_SHORT).show()
                if (!snapshot.exists()) {
                    val id = databaseReference.push().key
                    val userData = id?.let { Users(it, username, phone, role, email, password) }
                    databaseReference.child(id!!).setValue(userData)
                    Toast.makeText(
                        activity,
                        "Account succesfully registered",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(activity, publicHomeActivity::class.java))
                    requireActivity().finish()
                } else {
                    Log.d("SignUp", "User does not exist")
                    Toast.makeText(
                        activity,
                        "Account failed created",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Database error !", Toast.LENGTH_SHORT).show()
            }
        })
    }
}