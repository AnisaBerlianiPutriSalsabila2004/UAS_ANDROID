package com.example.projek_alsa
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(act: homeScreen) : FragmentStateAdapter(act) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> Home()
            1 -> Profile()
            else -> throw IllegalArgumentException("Position out of array")
        }
    }
}