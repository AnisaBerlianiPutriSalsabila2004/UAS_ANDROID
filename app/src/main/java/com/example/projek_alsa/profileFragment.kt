//package com.example.projek_alsa
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [profileFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class profileFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val fragmentProfile = inflater.inflate(R.layout.fragment_profile, container, false)
//
//        var username_profile = fragmentProfile.findViewById<TextView>(R.id.username_profile)
//        var email_profile = fragmentProfile.findViewById<TextView>(R.id.email_profile)
//        var phone_profile = fragmentProfile.findViewById<TextView>(R.id.phone_profile)
//
//        var usernames = homeScreen.names
//        var phones = homeScreen.phones
//        var emails = homeScreen.emails
//
//        username_profile.text = "Username : $usernames"
//        email_profile.text = "Phone : $phones"
//        phone_profile.text = "Email : $emails"
//
//        return fragmentProfile
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment Profile.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            profileFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}