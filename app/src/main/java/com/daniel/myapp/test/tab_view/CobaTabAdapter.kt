package com.daniel.myapp.test.tab_view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daniel.myapp.test.tab_view.frags.FirstFragment
import com.daniel.myapp.test.tab_view.frags.SecondFragment

class CobaTabAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2 // jumlah tab

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> FirstFragment()
        }
    }
}