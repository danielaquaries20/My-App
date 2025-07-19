package com.daniel.myapp.lifecycle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daniel.myapp.lifecycle.frags.HomeFragment
import com.daniel.myapp.lifecycle.frags.ProfileFragment

class TabAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2 // jumlah tab

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ProfileFragment()
            else -> HomeFragment()
        }
    }
}