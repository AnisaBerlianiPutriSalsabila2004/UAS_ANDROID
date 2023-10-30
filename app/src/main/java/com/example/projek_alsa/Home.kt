package com.example.projek_alsa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieList: ArrayList<film>
    private lateinit var movieAdapter: adapterFilm
    private lateinit var deskripsi: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentHome = inflater.inflate(R.layout.fragment_home, container, false)
        deskripsi = resources.getStringArray(R.array.list_deskripsi)


        var rView = fragmentHome.findViewById<RecyclerView>(R.id.recycle_view)
        rView.setHasFixedSize(true)
        rView.layoutManager = GridLayoutManager(activity, 2)

        movieList = ArrayList()
        movieList.add(film(R.drawable.img1, R.drawable.bg_1, "Girly, Comedy, Politics", "Barbie", "Greta Gerwig", R.drawable.rate5, "5",  deskripsi[0], "1h 54m", "R17+"))
        movieList.add(film(R.drawable.img2, R.drawable.bg_2,"Action, Sci Fi, Racing","Fast X", "Louis Leterrier", R.drawable.rate5, "5", deskripsi[1], "2h 21m", "R18+"))
        movieList.add(film(R.drawable.img3, R.drawable.bg_3,"Romance, Drama", "Ketika Berhenti Disini","Umay Shahab", R.drawable.rate3, "3", deskripsi[2], "1h 42m", "R15+"))
        movieList.add(film(R.drawable.img4, R.drawable.bg_4, "Family, Adventure","Petualngan Sherina 2", "Riri Riza", R.drawable.rate5, "5", deskripsi[3], "2h 6m", "R7+"))
        movieList.add(film(R.drawable.img5, R.drawable.bg_5, "Comedy, Family","The Super Mario bros", "Aaron Horvath", R.drawable.rate4, "4",  deskripsi[4], "1h 32m", "R10+"))
        movieList.add(film(R.drawable.img6, R.drawable.bg_6, "Action, Fighting, Anime","Kiko the Series", "sunghoo park", R.drawable.rate5, "5", deskripsi[5], "1h 45m", "R13+"))

        movieAdapter = adapterFilm(movieList)
        rView.adapter = movieAdapter

        val username_txt = fragmentHome.findViewById<TextView>(R.id.username)
        username_txt.text = homeScreen.names

        movieAdapter.onItemClick = {
            val intentToDescription = Intent(activity, filmDescription::class.java)
            intentToDescription.putExtra("movie", it)
            startActivity(intentToDescription)
        }

        // Inflate the layout for this fragment
        return fragmentHome
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}